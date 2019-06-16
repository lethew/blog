package studio.greeks.blog;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Example;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import studio.greeks.blog.model.Article;
import studio.greeks.blog.model.Comment;
import studio.greeks.blog.model.Link;
import studio.greeks.blog.model.Tag;
import studio.greeks.blog.repository.ArticleRepository;
import studio.greeks.blog.repository.CommentRepository;
import studio.greeks.blog.repository.LinkRepository;
import studio.greeks.blog.repository.TagRepository;

import java.io.BufferedReader;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/10 22:51
 */
//@Component
public class ImportRunner implements ApplicationRunner {

    @Autowired private TagRepository tagRepository;
    @Autowired private ArticleRepository articleRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private LinkRepository linkRepository;

    private Map<String, Article> articleHashMap = new HashMap<>();
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/b3blog?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true", "root", "123456");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from b3blog.b3_solo_link");
        while(resultSet.next()){
            Link link = new Link();
            link.setTitle(resultSet.getString("linkTitle"));
            link.setAddress(resultSet.getString("linkAddress"));
            link.setDescription(resultSet.getString("linkDescription"));
            link.setCreateTime(new Date());
            link.setUpdateTime(new Date());
            linkRepository.save(link);
        }
//        ResultSet resultSet = statement.executeQuery("select * from b3_solo_article");
//        while (resultSet.next()){
//            Article article = new Article();
//            article.setTitle(resultSet.getString("articleTitle"));
//            article.setSummary(resultSet.getString("articleAbstract"));
//            article.setContent(resultSet.getString("articleContent"));
//            article.setCreateTime(resultSet.getTimestamp("articleCreateDate"));
//            article.setUpdateTime(resultSet.getTimestamp("articleUpdateDate"));
//            article.setViewTimes(resultSet.getLong("articleViewCount"));
//            article.setPublished(resultSet.getBoolean("articleIsPublished"));
//            article.setPutTop(resultSet.getBoolean("articlePutTop"));
//            article.setViewPassword(resultSet.getString("articleViewPwd"));
//            article.setEditorType(resultSet.getString("articleEditorType"));
//            String tags = resultSet.getString("articleTags");
//            Set<Tag> tagSet = new HashSet<>();
//            for (String tagName : tags.split(",")) {
//                Tag tag = new Tag();
//                tag.setName(tagName);
//                Optional<Tag> one = tagRepository.findOne(Example.of(tag));
//                if(one.isPresent()){
//                    tag = one.get();
//                }else {
//                    tag = tagRepository.save(tag);
//                }
//                tagSet.add(tag);
//            }
//            article.setTags(tagSet);
//            Article save = articleRepository.save(article);
//            articleHashMap.put(save.getTitle(), save);
//        }
//        resultSet = statement.executeQuery("SELECT c.*, a.articleTitle FROM b3blog.b3_solo_comment c left join b3_solo_article a on c.commentOnId = a.oid");
//        Map<String, Comment> commentMap = new HashMap<>();
//        while (resultSet.next()){
//            Comment comment = new Comment();
//            comment.setContent(resultSet.getString("commentContent"));
//            comment.setArticle(articleHashMap.get(resultSet.getString("articleTitle")));
//            comment.setAuthor(resultSet.getString("commentName"));
//            comment.setEmail(resultSet.getString("commentEmail"));
//            comment.setCreateTime(resultSet.getTimestamp("commentDate"));
//            comment = commentRepository.save(comment);
//            String oid = resultSet.getString("oid");
//            commentMap.put(oid, comment);
//            String originalId = resultSet.getString("commentOriginalCommentId");
//            if(Strings.isBlank(originalId)){
//                comment.setRootId(comment.getId());
//                comment.setParentId(comment.getId());
//            }else{
//                Comment oComment = commentMap.get(originalId);
//                comment.setRootId(oComment.getRootId());
//                comment.setParentId(oComment.getId());
//            }
//            commentRepository.save(comment);
//        }

    }
}
