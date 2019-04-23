package fi.tuni.bloggadeback;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This class contains all required information for comment
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Comment {

    /**
     * Id for comment
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Date and Time when comment was created
     */
    @CreationTimestamp
    LocalDateTime commentCreatedLocalDateTime;

    /**
     * Text for comment
     */
    private String text;

    /**
     * Blog post where comment belongs to
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blogPost_id")
    private BlogPost blogPost;

    /**
     * Constructor for comment
     */
    public Comment() {
    }

    /**
     * Constructor which requires comment text
     *
     * @param text
     */
    public Comment(String text) {
        super();
        setText(text);
    }

    /**
     * Returns comment id
     *
     * @return Comment's id
     */
    public long getId() {
        return id;
    }

    /**
     * Returns Date and Time when comment was created
     *
     * @return Date and Time
     */
    public LocalDateTime getCommentCreatedLocalDateTime() {
        return commentCreatedLocalDateTime;
    }

    /**
     * Sets value for commentCreatedLocalDateTime
     *
     * @param commentCreatedLocalDateTime Date and Time
     */
    public void setCommentCreatedLocalDateTime(LocalDateTime commentCreatedLocalDateTime) {
        this.commentCreatedLocalDateTime = commentCreatedLocalDateTime;
    }

    /**
     * Returns comment's text
     *
     * @return Comment's text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets value for text
     *
     * @param text Comment's text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Returns blog post where comment belongs to
     *
     * @return Blog post
     */
    public BlogPost getBlogPost() {
        return blogPost;
    }

    /**
     * Sets value for blog post
     *
     * @param blogPost Blog post
     */
    public void setBlogPost(BlogPost blogPost) {
        this.blogPost = blogPost;
    }

    /**
     * Sets value for id
     *
     * @param id Comment's id
     */
    public void setId(long id) {
        this.id = id;
    }
}
