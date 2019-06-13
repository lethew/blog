package studio.greeks.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import studio.greeks.blog.model.Comment;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/11 23:01
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
