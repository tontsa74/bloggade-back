package fi.tuni.bloggadeback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogPostRestController {
    @Autowired
    BlogPostRepository blogPostRepository;

    @RequestMapping(value = "/blogposts", method = RequestMethod.GET)
    public Iterable<BlogPost> get() {
        System.out.println("get blogposts");
        return blogPostRepository.findAll();
    }

    @RequestMapping(value = "/blogposts/search{keyword}", method = RequestMethod.GET)
    public List<BlogPost> get(@RequestParam String keyword) {

        System.out.println("search: " + keyword);

        return blogPostRepository.findByBlogTitleContaining(keyword);
    }

    @RequestMapping(value = "/addblogpost", method = RequestMethod.POST)
    public void addBlogPost(@RequestBody BlogPost blogPost) {
        System.out.println("addBlogPost()");
        blogPostRepository.save(blogPost);
    }
}
