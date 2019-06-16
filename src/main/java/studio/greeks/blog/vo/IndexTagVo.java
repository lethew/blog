package studio.greeks.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import studio.greeks.blog.model.Tag;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/12 23:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexTagVo {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private Integer size;

    public static IndexTagVo of(Tag tag){
        return new IndexTagVo(tag.getId(), tag.getName(), tag.getArticles().size());
    }
}
