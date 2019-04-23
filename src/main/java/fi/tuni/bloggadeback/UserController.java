package fi.tuni.bloggadeback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

/**
 * This class handler all user requests
 */
@RestController
public class UserController {

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
     * Saves comment to comment repository and joins blog post and comment together
     *
     * @param comment Comment to be saved
     */
    @CrossOrigin("*")
    @RequestMapping(value = "/api/private/user/comment", method = RequestMethod.POST)
    public void saveBlogComment(@RequestBody Comment comment) {
        System.out.println("saveBlogPost");
        BlogPost blogPost = blogPostRepository.findById(comment.getId()).get();
        Comment newComment = new Comment(comment.getText());
        newComment.setBlogPost(blogPost);

        blogPost.getComments().add(newComment);

        blogPostRepository.save(blogPost);

        commentRepository.save(newComment);
    }

}
