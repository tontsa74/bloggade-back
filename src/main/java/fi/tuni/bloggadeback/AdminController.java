package fi.tuni.bloggadeback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class handles all admin requests
 */
@RestController
public class AdminController {

    /**
     * Blog post repository instance
     */
    @Autowired
    private BlogPostRepository blogPostRepository;

    /**
     * Comment repository instance
     */
    @Autowired
    private CommentRepository commentRepository;

    /**
     * Saves blogPost to repository
     *
     * @param blogPost Object which contains required information for blog post
     */
    @CrossOrigin("*")
    @RequestMapping(value = "/api/private/admin/add", method = RequestMethod.POST)
    public void addBlogPost(@RequestBody BlogPost blogPost) {
        System.out.println("addBlogPost()");
        blogPostRepository.save(blogPost);
    }

    /**
     * Updates existing blogPost
     *
     * @param blogpost Modified blogPost
     */
    @CrossOrigin("*")
    @RequestMapping(value = "/api/private/admin/edit", method = RequestMethod.PUT)
    public void editBlogPost(@RequestBody BlogPost blogpost) {
        BlogPost tmpBlogPost = blogPostRepository.findById(blogpost.getId()).get();
        tmpBlogPost.clone(blogpost);
        blogPostRepository.save(tmpBlogPost);
    }

    /**
     * Removes blogPost from repository using id
     *
     * @param blogpostId BlodPost's id which will be removed
     */
    @CrossOrigin("*")
    @RequestMapping(value = "/api/private/admin/delete/{blogpostId}", method = RequestMethod.DELETE)
    public void deleteBlogPost(@PathVariable long blogpostId) {
        BlogPost tmpBlogPost = blogPostRepository.findById(blogpostId).get();
        commentRepository.deleteAll(commentRepository.findCommentsByBlogPost_Id(blogpostId));
        blogPostRepository.delete(tmpBlogPost);
    }

    /**
     * Removes comment from repository using id
     *
     * @param commentId Comment's id which will be removed
     */
    //@CrossOrigin("*")
    @RequestMapping(value = "/api/private/admin/delete/comment/{commentId}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable long commentId) {
        Comment comment = commentRepository.findById(commentId).get();
        commentRepository.delete(comment);
    }

}

