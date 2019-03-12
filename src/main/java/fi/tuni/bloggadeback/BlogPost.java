package fi.tuni.bloggadeback;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BlogPost {
    @Id
    @GeneratedValue
    private long id;
    String userName;
    String blogTitle;
    String blogText;

    public BlogPost() {
        System.out.println("constructor BlogPost()");
    }

    public BlogPost(String userName, String blogTitle, String blogText) {
        System.out.println("constructor BlogPost(String userName, String blogText, String blogTitle)");
        setUserName(userName);
        setBlogTitle(blogTitle);
        setBlogText(blogText);
    }

    public long getId() {
        return id;
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

    public String getBlogText() {
        return blogText;
    }

    public void setBlogText(String blogText) {
        this.blogText = blogText;
    }
}
