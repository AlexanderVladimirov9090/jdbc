package com.clouway.article.persistence_layer;

import com.clouway.article.domain_layer.Article;
import com.clouway.article.domain_layer.ArticleHistory;
import com.clouway.article.domain_layer.ArticleRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by clouway on 21.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         Conrete implementation of ArticleRepository interface for working with sql databases.
 */
public class PersistenceArticleRepository implements ArticleRepository {
    private final DataStore dataStore;

    public PersistenceArticleRepository(Connection connection) {
        this.dataStore = new DataStore(connection);
    }

    /**
     * Gets all articles of database.
     *
     * @return list of all articles.
     */
    public List<Article> allArticles() {
        return dataStore.fetchRows("SELECT * FROM article", resultSet -> new Article(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
    }

    /**
     * Insert in to database Article.
     *
     * @param article new article that is going to be inserted.
     */
    public void insert(Article article) {
        dataStore.update("INSERT INTO article VALUES (?,?,?,?)", article.id, article.title, article.body, article.authorName);
    }

    /**
     * Updates Article in database.
     *
     * @param article    with new updated fields to be pushed in to database.
     */
    public void update(Article article) {
    dataStore.update("UPDATE article SET Title = ?, Body = ?, Author_FirstName = ? WHERE ID = ? ",article.title,article.body,article.authorName,article.id);
    }

    /**
     * Gets history of Article changes.
     *
     * @param limit  limits of history requested.
     * @param offset from where to start the requested history
     * @return history of article changes with limit and offset.
     */
    public List getHistory(int limit, int offset) {
       return dataStore.fetchRows("SELECT * FROM article_history LIMIT ? OFFSET ?;", resultSet -> new ArticleHistory(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)), limit, offset);

    }

    /**
     * Deletes record from database by id.
     *
     * @param id of record.
     */
    public void delete(int id) {
        dataStore.update("DELETE FROM article WHERE ID = ?",id);
    }
}
