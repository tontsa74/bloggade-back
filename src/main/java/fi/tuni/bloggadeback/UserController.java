package fi.tuni.bloggadeback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private CommentRepository commentRepository;

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
