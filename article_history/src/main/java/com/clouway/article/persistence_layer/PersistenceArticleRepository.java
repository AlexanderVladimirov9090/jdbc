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
     * @param id         of new article.
     * @param title      of new article.
     * @param body       of new article.
     * @param authorName of new article.
     */
    public void insert(int id, String title, String body, String authorName) {
        dataStore.update("INSERT INTO article VALUES (?,?,?,?)", id, title, body, authorName);
    }

    /**
     * Updates Article in databse.
     *
     * @param title      new title.
     * @param body       new body.
     * @param authorName new name of author.
     * @param id         search parammeter.
     */
    public void update(String title, String body, String authorName, int id) {
    dataStore.update("UPDATE article SET Title = ?, Body = ?, Author_FirstName = ? WHERE ID = ? ",title,body,authorName,id);
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
