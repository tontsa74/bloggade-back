package fi.tuni.bloggadeback;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    @ManyToOne
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
