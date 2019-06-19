package studio.greeks.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import studio.greeks.blog.model.Comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/17 22:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailCommentVo {
    private Integer id;
    private String content;
    private String author;
    private Date createTime;
    private String target;

    public static DetailCommentVo of(Comment comment){
        return new DetailCommentVo(comment.getId(), comment.getContent(), comment.getAuthor(), comment.getCreateTime(), null);
    }
}
