package studio.greeks.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import studio.greeks.blog.model.Article;
import studio.greeks.blog.model.Tag;
import studio.greeks.blog.repository.ArticleRepository;
import studio.greeks.blog.repository.TagRepository;
import studio.greeks.blog.restful.Pager;
import studio.greeks.blog.vo.IndexArticleVo;
import studio.greeks.blog.vo.IndexTagVo;

import java.util.*;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/11 23:12
 */
@Service
public class IndexService {
    private static final int PAGE_SIZE = 10;

    @Autowired private ArticleRepository articleRepository;
    @Autowired private TagRepository tagRepository;

    public Pager<IndexArticleVo> page(String tagStr, int pageIndex){
        Article example = new Article();
        example.setPublished(Boolean.TRUE);
        if(null != tagStr) {
            Tag exampleTag = new Tag();
            exampleTag.setName(tagStr);
            Optional<Tag> tag = tagRepository.findOne(Example.of(exampleTag));
            tag.ifPresent(t -> example.setTags(new HashSet<>(Collections.singletonList(t))));
            if(!tag.isPresent()){
                return new Pager<>();
            }
        }
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = PageRequest.of(pageIndex, PAGE_SIZE, sort);
        Page<Article> all = articleRepository.findAll(Example.of(example), pageRequest);
        Pager<IndexArticleVo> pager = new Pager<>(all);
        List<IndexArticleVo> vos = new ArrayList<>();
        for (Article article : all) {
            vos.add(IndexArticleVo.of(article));
        }
        pager.setData(vos);
        return pager;
    }

    public List<IndexTagVo> findAllTag(){
        List<Tag> all = tagRepository.findAll();
        List<IndexTagVo> tagVos = new ArrayList<>(all.size());
        for (Tag tag : all) {
            tagVos.add(IndexTagVo.of(tag));
        }
        return tagVos;
    }

    public List<IndexArticleVo> getHotsArticle() {
        return null;
    }
}
