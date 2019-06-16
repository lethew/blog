package studio.greeks.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import studio.greeks.blog.model.Link;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/16 18:39
 */
public interface LinkRepository extends JpaRepository<Link, Integer> {
}
