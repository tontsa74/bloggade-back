package fi.tuni.bloggadeback;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {
    List<BlogPost> findByBlogTitleContainingIgnoreCase(String keyword);
}
