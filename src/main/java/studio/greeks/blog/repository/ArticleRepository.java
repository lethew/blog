package studio.greeks.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import studio.greeks.blog.model.Article;
import studio.greeks.blog.model.Comment;
import studio.greeks.blog.model.Tag;

import java.util.List;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/10 23:06
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Page<Article> findAllByPublishedIsTrueOrderByCreateTimeDesc(Pageable pageable);
    Page<Article> findAllByTagsContainsAndPublishedIsTrueOrderByCreateTimeDesc(Tag tag, Pageable pageable);
    Page<Article> findAllByPublishedIsTrueOrderByViewTimesDesc(Pageable pageable);
}
