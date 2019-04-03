package fi.tuni.bloggadeback;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class BlogPost {
    @Id
    @GeneratedValue
    private long id;
    @CreationTimestamp
    LocalDateTime blogCreatedLocalDateTime;
    @UpdateTimestamp
    LocalDateTime blogModifiedLocalDateTime;
    String userName;
    String blogTitle;
    String blogDescription;
    String blogText;

    public BlogPost() {
        System.out.println("constructor BlogPost()");
    }

    public BlogPost(String userName, String blogTitle, String blogDescription, String blogText) {
        System.out.println("constructor BlogPost(String userName, String blogText, String blogTitle)");
        setUserName(userName);
        setBlogTitle(blogTitle);
        setBlogDescription(blogDescription);
        setBlogText(blogText);
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

    public void clone(BlogPost blogPost) {
        setBlogModifiedLocalDateTime(blogPost.getBlogModifiedLocalDateTime());
        setUserName(blogPost.getUserName());
        setBlogTitle(blogPost.getBlogTitle());
        setBlogDescription(blogPost.getBlogDescription());
        setBlogText(blogPost.getBlogText());
    }
}
