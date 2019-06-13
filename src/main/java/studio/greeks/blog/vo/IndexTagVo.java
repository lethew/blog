package studio.greeks.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import studio.greeks.blog.model.Tag;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/12 23:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexTagVo {
    private Integer id;
    private String name;
    private Integer size;

    public static IndexTagVo of(Tag tag){
        return new IndexTagVo(tag.getId(), tag.getName(), tag.getArticles().size());
    }
}
