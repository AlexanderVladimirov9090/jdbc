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
     * @param article  new article that is inserted to database.
     */
    void insert(Article article);
    /**
     * Updated article by searched by id.
     *
     * @param article     article with updated fields to be pushed to database.
     */
    void update(Article article);

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