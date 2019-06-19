package studio.greeks.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.greeks.blog.restful.Response;
import studio.greeks.blog.service.DetailService;
import studio.greeks.blog.service.IndexService;
import studio.greeks.blog.vo.DetailArticleVo;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/13 22:41
 */
@Controller
@RequestMapping("/detail")
public class DetailController {

    @Autowired private DetailService detailService;
    @Autowired private IndexService indexService;

    @GetMapping("/{id}.html")
    public String detail(Model model, @PathVariable Integer id){
        DetailArticleVo detailArticleVo = detailService.getArticle(id);
        model.addAttribute("fragment", indexService.getFragmentVo(detailArticleVo.getTitle(), "index"));
        model.addAttribute("article", detailArticleVo);
        return "detail.html";
    }

    @GetMapping("/comment/{id}/{pageIndex}")
    @ResponseBody
    public Response page(@PathVariable Integer id, @PathVariable Integer pageIndex){
        return Response.success(detailService.getComment(id, pageIndex));
    }
}
