package fi.tuni.bloggadeback;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.origin.SystemEnvironmentOrigin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    //List<Comment> comments;   // TODO: implement comments propelly
    String[] comments;

    public BlogPost() {
        System.out.println("constructor BlogPost()");
    }

    public BlogPost(String userName, String blogTitle, String blogDescription, String blogText, String[] comments) {// List<Comment> comments
        System.out.println("constructor BlogPost");
        setUserName(userName);
        setBlogTitle(blogTitle);
        setBlogDescription(blogDescription);
        setBlogText(blogText);
        //this.comments = new ArrayList<>();
        //this.comments.addAll(comments);
        this.comments = comments;
//        comments = new String[10];
//        setComments(comments);
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

/*    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments.addAll(comments);
    }

    public void setComment(String comment) {
        Comment newComment = new Comment(comment);
        this.comments.add(newComment);
    }*/

    public String[] getComments() {
        return comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
        System.out.println(this.comments);
    }

    public void setComment(String comment) {
        this.comments[0] = comment;
    }

    public void clone(BlogPost blogPost) {
        setBlogModifiedLocalDateTime(blogPost.getBlogModifiedLocalDateTime());
        setUserName(blogPost.getUserName());
        setBlogTitle(blogPost.getBlogTitle());
        setBlogDescription(blogPost.getBlogDescription());
        setBlogText(blogPost.getBlogText());
    }
}
