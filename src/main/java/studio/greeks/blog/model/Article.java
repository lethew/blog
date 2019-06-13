package studio.greeks.blog.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/10 22:05
 */
@Getter
@Setter
@Entity
@Table(name = "t_blog_article")
public class Article {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @Lob
    @Column(columnDefinition = "text")
    private String summary;
    @Lob
    @Column(columnDefinition = "mediumtext")
    private String content;
    private Date createTime;
    private Date updateTime;
    private Long viewTimes;
    private Boolean published;
    private Boolean putTop;
    private String viewPassword;
    private String editorType;
    @OneToMany(mappedBy = "article")
    private Set<Comment> comments;

    @ManyToMany
    @JoinTable(name="t_blog_article_tag",
            inverseJoinColumns = {@JoinColumn(name="tag_id",referencedColumnName="id")},
            joinColumns = {@JoinColumn(name="article_id",referencedColumnName="id")},
            foreignKey = @ForeignKey(name = "null")
    )
    private Set<Tag> tags;
}
