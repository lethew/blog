package studio.greeks.blog.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/16 18:18
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class FragmentVo {
    @NonNull
    private String title;
    @NonNull
    private String current;
    private List<IndexTagVo> tags;
    private List<IndexArticleVo> hots;
    private List<IndexLinkVo> friends;
    private String aboutMe;
}
