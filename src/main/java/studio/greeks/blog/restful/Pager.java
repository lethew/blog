package studio.greeks.blog.restful;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/11 23:58
 */
@Data
@NoArgsConstructor
public class Pager<T> {
    private boolean next;
    private List<T> data = new ArrayList<>();

    public Pager(Page page) {
        next = page.hasNext();
    }
}
