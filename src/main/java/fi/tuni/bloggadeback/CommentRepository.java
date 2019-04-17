package fi.tuni.bloggadeback;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findCommentsByBlogPost_Id(long id);
}
