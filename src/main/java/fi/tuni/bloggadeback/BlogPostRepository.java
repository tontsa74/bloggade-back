package fi.tuni.bloggadeback;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * This is repository for BlogPosts
 */
public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {

    /**
     * Finds blogPosts from repository which contains the given string
     *
     * @param keyword user's word for searching
     * @return founded BlogPosts by keyword
     */
    List<BlogPost> findByBlogTitleContainingIgnoreCase(String keyword); // TODO: add search from description and text
}
