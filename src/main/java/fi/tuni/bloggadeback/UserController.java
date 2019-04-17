package fi.tuni.bloggadeback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private CommentRepository commentRepository;
//
//    @CrossOrigin("*")
//    @GetMapping(value = "/api/public/comment/{id}{blogComment}")
//    public void saveComment(@PathVariable Long id, @PathVariable String blogComment) {
//        System.out.println("saveBlogPost2");
//        BlogPost tmpBlogPost = blogPostRepository.findById(id).get();
//        tmpBlogPost.setComment(blogComment);
//    }

    @CrossOrigin("*")
    @RequestMapping(value = "/api/public/comment", method = RequestMethod.POST)
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
