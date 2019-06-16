package studio.greeks.blog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/16 18:37
 */
@Getter
@Setter
@Entity
@Table(name = "t_blog_link")
public class Link {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String address;
    private String description;
    private Date createTime;
    private Date updateTime;
}
