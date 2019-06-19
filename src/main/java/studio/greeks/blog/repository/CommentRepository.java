package studio.greeks.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import studio.greeks.blog.model.Article;
import studio.greeks.blog.model.Comment;

import java.util.List;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/11 23:01
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Page<Comment> findAllByArticle_Id(Integer articleId, PageRequest pageRequest);
}
