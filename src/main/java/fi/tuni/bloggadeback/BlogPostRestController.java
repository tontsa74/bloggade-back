package fi.tuni.bloggadeback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogPostRestController {
    @Autowired
    BlogPostRepository blogPostRepository;

    @RequestMapping(value = "/blogposts", method = RequestMethod.GET)
    public Iterable<BlogPost> get() {
        System.out.println("get blogposts");
        return blogPostRepository.findAll();
    }
}
