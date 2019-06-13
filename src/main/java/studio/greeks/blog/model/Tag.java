package studio.greeks.blog.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/10 22:10
 */
@Getter
@Setter
@Entity
@Table(name = "t_blog_tag")
public class Tag {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany
    @JoinTable(name="t_blog_article_tag",
            joinColumns= {@JoinColumn(name="tag_id",referencedColumnName="id")},
            inverseJoinColumns= {@JoinColumn(name="article_id",referencedColumnName="id")},
            foreignKey = @ForeignKey(name = "null")
    )
    private Set<Article> articles;
}
