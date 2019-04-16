package fi.tuni.bloggadeback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogPostRestController {
    @Autowired
    BlogPostRepository blogPostRepository;

    @CrossOrigin("*")
    @RequestMapping(value = "/public/blogposts", method = RequestMethod.GET)
    public Iterable<BlogPost> get() {
        System.out.println("get blogposts");
        return blogPostRepository.findAll();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/public/blogposts/search{keyword}", method = RequestMethod.GET)
    public Iterable<BlogPost> get(@RequestParam String keyword) {

        System.out.println("search keyword: " + keyword);

        return blogPostRepository.findByBlogTitleContainingIgnoreCase(keyword);
    }
}
