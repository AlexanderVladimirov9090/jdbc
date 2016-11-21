package com.clouway.article.domain_layer;

import java.util.List;

/**
 * Created by clouway on 21.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This interface is used to work with all database types for Article domain_layer.
 */
public interface ArticleRepository {

    /**
     * Gets all articles from database.
     *
     * @return list of articles.
     */
    List<Article> allArticles();

    /**
     * Insert in to database Article.
     * @param id of new article.
     * @param title of new article.
     * @param body of new article.
     * @param authorName of new article.
     */
    void insert(int id, String title ,String body, String authorName);
    /**
     * Updated article by searched by id.
     *
     * @param Title      new title.
     * @param body       new body.
     * @param authorName new name of author.
     */
    void update(String Title, String body, String authorName, int id);

    /**
     * Gets history of Articles changes.
     *
     * @param limit  limits of history requested.
     * @param offset from where to start the requested history
     * @return history of article changes with limit and offset.
     */
    List getHistory(int limit, int offset);

    /**
     * Delete article by id;
     */
    void delete(int id);
}