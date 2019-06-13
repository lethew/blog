package studio.greeks.blog.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/10 22:10
 */
@Getter
@Setter
@Entity
@Table(name = "t_blog_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private String author;
    private String email;
    private Date createTime;
    private Integer rootId;
    private Integer parentId;
    @ManyToOne
    @JoinColumn(name = "article_id", foreignKey = @ForeignKey(name = "null"))
    private Article article;
}
