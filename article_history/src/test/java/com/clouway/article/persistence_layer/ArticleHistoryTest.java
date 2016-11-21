package com.clouway.article.persistence_layer;

import com.clouway.article.domain_layer.ArticleHistory;
import com.clouway.article.domain_layer.ArticleRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 21.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class ArticleHistoryTest {
    @Rule
    public DataBaseConnectionRule dataBaseConnectionRule = new DataBaseConnectionRule();
    private Connection connection = dataBaseConnectionRule.connection;
    private DataStore dataStore = new DataStore(connection);
    private ArticleRepository articleRepository = new PersistenceArticleRepository(connection);
    public ArticleHistoryTest() throws SQLException {
    }

    @Before
    public void cleanAndPopulateTable(){
        dataStore.update("TRUNCATE TABLE article");
        //dataStore.update("TRUNCATE TABLE article_history");

        articleRepository.insert(1,"title1","body1","author1");
        articleRepository.insert(2,"title2","body2","author2");
        articleRepository.insert(3,"title3","body3","author3");

    }
    @Test
    public void getFirstHistory(){
        ArticleHistory expected = new ArticleHistory(1,1,"title1","body1","author1");
        articleRepository.update("newTitle","newBody","newAuthor",1);
        List<ArticleHistory> articleHistories = articleRepository.getHistory(1,1);
        ArticleHistory actual = articleHistories.get(0);
        assertThat(actual.equals(expected),is(true));
    }

}
