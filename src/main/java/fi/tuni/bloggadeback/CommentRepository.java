package fi.tuni.bloggadeback;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * This is repository for comments
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {

    /**
     * Find comments from repository using blog post's id
     *
     * @param id blog post's id
     * @return List of found comments
     */
    List<Comment> findCommentsByBlogPost_Id(long id);
}
