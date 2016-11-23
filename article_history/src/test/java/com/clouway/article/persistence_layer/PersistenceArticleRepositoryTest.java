package com.clouway.article.persistence_layer;

import com.clouway.article.domain_layer.Article;
import com.clouway.article.domain_layer.ArticleRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by clouway on 21.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class PersistenceArticleRepositoryTest {
    @Rule
    public DataBaseConnectionRule dataBaseConnectionRule = new DataBaseConnectionRule();
    private Connection connection = dataBaseConnectionRule.connection;
    private DataStore dataStore = new DataStore(connection);
    private ArticleRepository articleRepository = new PersistenceArticleRepository(connection);
    public PersistenceArticleRepositoryTest() throws SQLException {
    }

    @Before
    public void cleanAndPopulateTable(){
        dataStore.update("TRUNCATE TABLE article");
        articleRepository.insert(new Article(1,"title1","body1","autor1"));
        articleRepository.insert(new Article(2,"title2","body2","autor2"));
        articleRepository.insert(new Article(3,"title3","body3","autor3"));

    }

    @Test
    public void updateArticle(){
        Article expected = new Article(1,"newTitle","newBody","newString");
        articleRepository.update(new Article(1,"newTitle","newBody","newString"));
        List<Article> articles = articleRepository.allArticles();
        Article actual = articles.get(0);
        assertThat(actual.equals(expected),is(true));
    }

    @Test
    public void deleteArticle(){
        articleRepository.delete(3);
        List<Article> articles = articleRepository.allArticles();
        assertThat(articles.size(),is(equalTo(2)));
    }

}