package com.clouway.article.persistence_layer;

import com.clouway.article.domain_layer.Article;
import com.clouway.article.domain_layer.ArticleHistory;
import com.clouway.article.domain_layer.ArticleRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
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
    public void cleanAndPopulateTable() {
        dataStore.update("TRUNCATE TABLE article");

    }

    @Test
    public void getFirstHistory() {
        articleRepository.insert(new Article(1, "title1", "body1", "author1"));
        ArticleHistory expected = new ArticleHistory(1, 1, "title1", "body1", "author1");
        articleRepository.update(new Article(1, "newTitle", "newBody", "newAuthor"));
        List<ArticleHistory> articleHistories = articleRepository.getHistory(1, 0);
        ArticleHistory actual = articleHistories.get(0);
        assertThat(actual.equals(expected), is(true));
    }

    @Test
    public void getHistoryWithOffsetAndLimit() {
        articleRepository.insert(new Article(1, "old", "old", "old"));
        articleRepository.insert(new Article(2, "title2", "body2", "author2"));
        articleRepository.insert(new Article(3, "title3", "body3", "author3"));

        ArticleHistory expectedFirst = new ArticleHistory(2, 1, "title1", "body1", "author1");
        ArticleHistory expectedSecond = new ArticleHistory(3, 1, "newtitle1", "newbody1", "newauthor1");
        List<ArticleHistory> expected = new LinkedList();
        expected.add(expectedFirst);
        expected.add(expectedSecond);
        articleRepository.update(new Article(1,"newTitle", "newBody", "newAuthor"));
        articleRepository.update(new Article(2,"new","new","new"));
        List<ArticleHistory> actual = articleRepository.getHistory(2, 1);
        assertThat(actual,is(equalTo(expected)));
    }

}
