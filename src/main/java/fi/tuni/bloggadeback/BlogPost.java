package fi.tuni.bloggadeback;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    LocalDateTime blogCreatedLocalDateTime;

    @UpdateTimestamp
    LocalDateTime blogModifiedLocalDateTime;

    String userName;
    String blogTitle;
    String blogDescription;
    String blogText;

    @OneToMany(mappedBy = "blogPost")
    private List<Comment> comments;   // TODO: implement comments propelly


    public BlogPost() { }

    public BlogPost(String userName, String blogTitle, String blogDescription, String blogText) {
        super();
        this.userName = userName;
        this.blogTitle = blogTitle;
        this.blogDescription = blogDescription;
        this.blogText = blogText;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getBlogCreatedLocalDateTime() {
        return blogCreatedLocalDateTime;
    }

    public LocalDateTime getBlogModifiedLocalDateTime() {
        return blogModifiedLocalDateTime;
    }

    public void setBlogModifiedLocalDateTime(LocalDateTime dateTime) {
        blogModifiedLocalDateTime = dateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogDescription() {
        return blogDescription;
    }

    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }

    public String getBlogText() {
        return blogText;
    }

    public void setBlogText(String blogText) {
        this.blogText = blogText;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComment(String comment) {
        this.comments.add(new Comment(comment));
    }

    public void clone(BlogPost blogPost) {
        setBlogModifiedLocalDateTime(blogPost.getBlogModifiedLocalDateTime());
        setUserName(blogPost.getUserName());
        setBlogTitle(blogPost.getBlogTitle());
        setBlogDescription(blogPost.getBlogDescription());
        setBlogText(blogPost.getBlogText());
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBlogCreatedLocalDateTime(LocalDateTime blogCreatedLocalDateTime) {
        this.blogCreatedLocalDateTime = blogCreatedLocalDateTime;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
