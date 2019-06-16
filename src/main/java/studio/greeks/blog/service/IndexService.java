package studio.greeks.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import studio.greeks.blog.model.Article;
import studio.greeks.blog.model.Link;
import studio.greeks.blog.model.Tag;
import studio.greeks.blog.repository.ArticleRepository;
import studio.greeks.blog.repository.LinkRepository;
import studio.greeks.blog.repository.TagRepository;
import studio.greeks.blog.restful.Pager;
import studio.greeks.blog.vo.FragmentVo;
import studio.greeks.blog.vo.IndexArticleVo;
import studio.greeks.blog.vo.IndexLinkVo;
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
    @Autowired private LinkRepository linkRepository;

    public FragmentVo getFragmentVo(String title, String current){
        FragmentVo fragmentVo = new FragmentVo(title, current);
        fragmentVo.setTags(findAllTag());
        fragmentVo.setHots(getHotsArticle());
        fragmentVo.setFriends(findAllLinks());
        fragmentVo.setHots(getHotsArticle());
        return fragmentVo;
    }

    public Pager<IndexArticleVo> page(String tagStr, int pageIndex){
        Tag tag = null == tagStr?null:tagRepository.getFirstByName(tagStr).orElse(null);
        PageRequest pageRequest = PageRequest.of(pageIndex, PAGE_SIZE);
        Page<Article> all = tag == null
                ?articleRepository.findAllByPublishedIsTrueOrderByCreateTimeDesc(pageRequest)
                :articleRepository.findAllByTagsContainsAndPublishedIsTrueOrderByCreateTimeDesc(tag, pageRequest);

        Pager<IndexArticleVo> pager = new Pager<>(all);
        List<IndexArticleVo> vos = new ArrayList<>();
        for (Article article : all) {
            vos.add(IndexArticleVo.of(article));
        }
        pager.setData(vos);
        return pager;
    }

    private List<IndexTagVo> findAllTag(){
        List<Tag> all = tagRepository.findAll();
        List<IndexTagVo> tagVos = new ArrayList<>(all.size());
        for (Tag tag : all) {
            tagVos.add(IndexTagVo.of(tag));
        }
        return tagVos;
    }
    private List<IndexLinkVo> findAllLinks(){
        List<Link> all = linkRepository.findAll();
        List<IndexLinkVo> linkVos = new ArrayList<>(all.size());
        for (Link link : all) {
            linkVos.add(IndexLinkVo.of(link));
        }
        return linkVos;
    }
    private List<IndexArticleVo> getHotsArticle() {
        PageRequest pageRequest = PageRequest.of(0, PAGE_SIZE);
        Page<Article> all = articleRepository.findAllByPublishedIsTrueOrderByViewTimesDesc(pageRequest);
        List<IndexArticleVo> vos = new ArrayList<>();
        for (Article article : all) {
            vos.add(IndexArticleVo.of(article));
        }
        return vos;
    }
}
