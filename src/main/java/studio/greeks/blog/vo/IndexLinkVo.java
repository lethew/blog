package studio.greeks.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import studio.greeks.blog.model.Link;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/16 18:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexLinkVo {
    @NonNull
    private String title;
    @NonNull
    private String address;
    @NonNull
    private String description;

    public static IndexLinkVo of(Link link) {
        return new IndexLinkVo(link.getTitle(), link.getAddress(), link.getDescription());
    }
}
