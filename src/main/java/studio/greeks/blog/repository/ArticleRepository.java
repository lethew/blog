package studio.greeks.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import studio.greeks.blog.model.Article;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/10 23:06
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
