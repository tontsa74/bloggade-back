package fi.tuni.bloggadeback;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * This class contains all required information for blogPost
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class BlogPost {

    /**
     * Id for blog post
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Date and Time when blog post is created
     */
    @CreationTimestamp
    LocalDateTime blogCreatedLocalDateTime;

    /**
     * Date and Time when blog post was last modified
     */
    @UpdateTimestamp
    LocalDateTime blogModifiedLocalDateTime;

    /**
     * Author for blog post
     */
    String userName;

    /**
     * Title for blog post
     */
    String blogTitle;

    /**
     * Description for blog post
     */
    String blogDescription;

    /**
     * Text for blog post
     */
    String blogText;

    /**
     * Blog post's comments
     */
    @OneToMany(mappedBy = "blogPost")
    @OrderBy("id DESC")
    private Set<Comment> comments;

    /**
     * Constructor for BlogPost
     */
    public BlogPost() {
    }

    /**
     * Constructor for BlogPost which initilizes all required properties
     *
     * @param userName Author for blog post
     * @param blogTitle Title for blog post
     * @param blogDescription Description for blog post
     * @param blogText Text for blog post
     */
    public BlogPost(String userName, String blogTitle, String blogDescription, String blogText) {
        super();
        this.userName = userName;
        this.blogTitle = blogTitle;
        this.blogDescription = blogDescription;
        this.blogText = blogText;
        comments  = new LinkedHashSet<>();
    }

    /**
     * Returns blog post's id
     *
     * @return Blog post's id
     */
    public long getId() {
        return id;
    }

    /**
     * Returns Date and Time when blog post was created
     *
     * @return Date and Time
     */
    public LocalDateTime getBlogCreatedLocalDateTime() {
        return blogCreatedLocalDateTime;
    }

    /**
     * Returns Date and Time when blog post was last modified
     *
     * @return Date and Time
     */
    public LocalDateTime getBlogModifiedLocalDateTime() {
        return blogModifiedLocalDateTime;
    }

    /**
     * Sets value for blogModifiedLocalDateTime
     *
     * @param dateTime Date and Time
     */
    public void setBlogModifiedLocalDateTime(LocalDateTime dateTime) {
        blogModifiedLocalDateTime = dateTime;
    }

    /**
     * Returns blog post's author
     *
     * @return Blog post's author
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets value for username
     *
     * @param userName Blog post's author
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns blog posts title
     *
     * @return Blog post's title
     */
    public String getBlogTitle() {
        return blogTitle;
    }

    /**
     * Set value for blogTitle
     *
     * @param blogTitle Blog post's title
     */
    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    /**
     * Returns blog post's description
     *
     * @return Blog post's description
     */
    public String getBlogDescription() {
        return blogDescription;
    }

    /**
     * Sets value for blogDescription
     *
     * @param blogDescription Blog post's description
     */
    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }

    /**
     * Returns blog post's text
     *
     * @return Blog post's text
     */
    public String getBlogText() {
        return blogText;
    }

    /**
     * Sets value for blogText
     *
     * @param blogText Blog post's text
     */
    public void setBlogText(String blogText) {
        this.blogText = blogText;
    }

    /**
     * Returns blog post comments
     *
     * @return Set of comments
     */
    public Set<Comment> getComments() {
        return comments;
    }

    /**
     * Add single comment to comment set
     *
     * @param comment Comment text
     */
    public void setComment(String comment) {
        this.comments.add(new Comment(comment));
    }

    /**
     * Clones values from other blog post
     *
     * @param blogPost Other blog post
     */
    public void clone(BlogPost blogPost) {
        setBlogModifiedLocalDateTime(blogPost.getBlogModifiedLocalDateTime());
        setUserName(blogPost.getUserName());
        setBlogTitle(blogPost.getBlogTitle());
        setBlogDescription(blogPost.getBlogDescription());
        setBlogText(blogPost.getBlogText());
    }

    /**
     * Sets value for id
     *
     * @param id Blog post id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets value for blogCreatedLocalDateTime
     *
     * @param blogCreatedLocalDateTime Date and Time when blog post is created
     */
    public void setBlogCreatedLocalDateTime(LocalDateTime blogCreatedLocalDateTime) {
        this.blogCreatedLocalDateTime = blogCreatedLocalDateTime;
    }

    /**
     * Sets value for comments set
     *
     * @param comments Set of comments
     */
    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
