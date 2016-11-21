package com.clouway.article.domain_layer;

import java.util.Objects;

/**
 * Created by clouway on 21.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class ArticleHistory {
    private final Integer id;
    private final Integer articleId;
    private final String title;
    private final String body;
    private final String authorName;

    public ArticleHistory(Integer id, Integer articleId, String title, String body, String authorName) {
        this.id = id;
        this.articleId = articleId;
        this.title = title;
        this.body = body;
        this.authorName = authorName;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!ArticleHistory.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final ArticleHistory other = (ArticleHistory) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return !(!Objects.equals(this.articleId,other.articleId)&&!Objects.equals(this.title, other.title) && !Objects.equals(this.body, other.body) && !Objects.equals(this.authorName, other.authorName));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 53 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 53 * hash + (this.body != null ? this.body.hashCode() : 0);
        hash = 53 * hash + (this.authorName != null ? this.authorName.hashCode() : 0);
        return hash;
    }

}
