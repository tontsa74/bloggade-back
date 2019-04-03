package fi.tuni.bloggadeback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    private BlogPostRepository blogPostRepository;

    //@CrossOrigin("*")
    @RequestMapping(value = "/api/private/admin/add", method = RequestMethod.POST)
    public void addBlogPost(@RequestBody BlogPost blogPost) {
        System.out.println("addBlogPost()");
        blogPostRepository.save(blogPost);
    }

    //@CrossOrigin("*")
    @RequestMapping(value = "/api/private/admin/edit", method = RequestMethod.PUT)
    public void editBlogPost(@RequestBody BlogPost blogpost) {
        BlogPost tmpBlogPost = blogPostRepository.findById(blogpost.getId()).get();
        tmpBlogPost.clone(blogpost);
        blogPostRepository.save(tmpBlogPost);
    }

    //@CrossOrigin("*")
    @RequestMapping(value = "/api/private/admin/delete/{blogpostId}", method = RequestMethod.DELETE)
    public void deleteBlogPost(@PathVariable long blogpostId) {
        BlogPost tmpBlogPost = blogPostRepository.findById(blogpostId).get();
        blogPostRepository.delete(tmpBlogPost);
    }

}

