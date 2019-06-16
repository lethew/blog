package studio.greeks.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import studio.greeks.blog.model.Article;
import studio.greeks.blog.model.Tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/12 0:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexArticleVo {
    private Integer id;
    private String title;
    private String summary;
    private Date createTime;
    private Long viewTimes;
    private Integer comments;
    private String editorType;
    private List<String> tags;

    public static IndexArticleVo of(Article article){
        IndexArticleVo articleVo = new IndexArticleVo(article.getId(), article.getTitle(), article.getSummary(),
                article.getCreateTime(), article.getViewTimes(),article.getComments().size(), article.getEditorType(), new ArrayList<>());
        for (Tag tag : article.getTags()) {
            articleVo.getTags().add(tag.getName());
        }
        return articleVo;
    }
}
