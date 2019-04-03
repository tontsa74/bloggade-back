package fi.tuni.bloggadeback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogPostRestController {
    @Autowired
    BlogPostRepository blogPostRepository;

    @CrossOrigin("*")
    @RequestMapping(value = "/api/public/blogposts", method = RequestMethod.GET)
    public Iterable<BlogPost> get() {
        System.out.println("get blogposts");
        return blogPostRepository.findAll();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/api/public/blogposts/search{keyword}", method = RequestMethod.GET)
    public Iterable<BlogPost> get(@RequestParam String keyword) {

        System.out.println("search keyword: " + keyword);

        return blogPostRepository.findByBlogTitleContainingIgnoreCase(keyword);
    }
}
