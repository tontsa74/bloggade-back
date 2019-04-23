package fi.tuni.bloggadeback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class handles blogpost browsing requests
 */
@RestController
@RequestMapping("/api")
public class BlogPostRestController {
    @Autowired
    BlogPostRepository blogPostRepository;

    /**
     * Returns all blogPosts from repository
     *
     * @return all existing blogPosts from repository
     */
    @CrossOrigin("*")
    @RequestMapping(value = "/public/blogposts", method = RequestMethod.GET)
    public Iterable<BlogPost> get() {
        System.out.println("get blogposts");
        return blogPostRepository.findAll();
    }

    /**
     * Searches blogPosts titles using keyword
     *
     * @param keyword user's word for searching
     * @return founded blogPosts by keyword
     */
    @CrossOrigin("*")
    @RequestMapping(value = "/public/blogposts/search{keyword}", method = RequestMethod.GET)
    public Iterable<BlogPost> get(@RequestParam String keyword) {

        System.out.println("search keyword: " + keyword);

        return blogPostRepository.findByBlogTitleContainingIgnoreCase(keyword);
    }
}
