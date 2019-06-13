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
 * @version blog@2019/6/13 22:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailArticleVo {
    private Integer id;
    private String title;
    private String content;
    private Date createTime;
    private Long viewTimes;
    private String editorType;
    private List<String> tags;

    public static DetailArticleVo of(Article article){
        DetailArticleVo articleVo = new DetailArticleVo(article.getId(), article.getTitle(), article.getContent(),
                article.getCreateTime(), article.getViewTimes(), article.getEditorType(), new ArrayList<>());
        for (Tag tag : article.getTags()) {
            articleVo.getTags().add(tag.getName());
        }
        return articleVo;
    }
}
