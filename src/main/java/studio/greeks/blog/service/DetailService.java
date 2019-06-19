package studio.greeks.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import studio.greeks.blog.model.Article;
import studio.greeks.blog.model.Comment;
import studio.greeks.blog.repository.ArticleRepository;
import studio.greeks.blog.repository.CommentRepository;
import studio.greeks.blog.restful.Pager;
import studio.greeks.blog.vo.DetailArticleVo;
import studio.greeks.blog.vo.DetailCommentVo;
import studio.greeks.blog.vo.IndexArticleVo;

import java.util.*;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/13 22:45
 */
@Service
public class DetailService {
    private static final int PAGE_SIZE = 10;
    @Autowired private ArticleRepository articleRepository;
    @Autowired private CommentRepository commentRepository;

    public DetailArticleVo getArticle(Integer id){
        Optional<Article> byId = articleRepository.findById(id);
        return byId.map(DetailArticleVo::of).orElse(null);
    }

    public Pager<DetailCommentVo> getComment(Integer articleId, Integer pageIndex){

        Optional<Article> byId = articleRepository.findById(articleId);
        if (byId.isPresent()) {
            Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
            PageRequest pageRequest = PageRequest.of(pageIndex, PAGE_SIZE, sort);
            Comment example = new Comment();
            example.setArticle(byId.get());
            Page<Comment> allByArticle = commentRepository.findAll(Example.of(example), pageRequest);
            Pager<DetailCommentVo> pager = new Pager<>(allByArticle);
            List<DetailCommentVo> vos = new ArrayList<>();
            for (Comment comment : allByArticle) {
                DetailCommentVo of = DetailCommentVo.of(comment);
                if(!comment.getParentId().equals(comment.getRootId())){
                    comment = commentRepository.getOne(comment.getParentId());
                    of.setTarget(comment.getAuthor());
                }
                vos.add(of);
            }
            pager.setData(vos);
            return pager;
        } else return null;
    }
}
