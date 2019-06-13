package studio.greeks.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.greeks.blog.model.Article;
import studio.greeks.blog.repository.ArticleRepository;
import studio.greeks.blog.vo.DetailArticleVo;

import java.util.Optional;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/13 22:45
 */
@Service
public class DetailService {
    @Autowired private ArticleRepository articleRepository;

    public DetailArticleVo getArticle(Integer id){
        Optional<Article> byId = articleRepository.findById(id);
        return byId.map(DetailArticleVo::of).orElse(null);
    }
}
