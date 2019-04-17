package fi.tuni.bloggadeback;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    LocalDateTime commentCreatedLocalDateTime;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blogPost_id")
    private BlogPost blogPost;

    public Comment() {
    }

    public Comment(String text) {
        super();
        setText(text);
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCommentCreatedLocalDateTime() {
        return commentCreatedLocalDateTime;
    }

    public void setCommentCreatedLocalDateTime(LocalDateTime commentCreatedLocalDateTime) {
        this.commentCreatedLocalDateTime = commentCreatedLocalDateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BlogPost getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(BlogPost blogPost) {
        this.blogPost = blogPost;
    }

    public void setId(long id) {
        this.id = id;
    }
}
