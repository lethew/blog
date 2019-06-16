package studio.greeks.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.greeks.blog.restful.Response;
import studio.greeks.blog.service.IndexService;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/11 23:10
 */
@Controller
public class IndexController {

    @Autowired private IndexService indexService;

    @RequestMapping({"/","/index.html","/index.htm"})
    public String index(Model model, String tag){
        model.addAttribute("fragment", indexService.getFragmentVo("首页", "index"));
        model.addAttribute("tag", null == tag?"":("："+tag));
        return "index";
    }

    @GetMapping("/article/{pageIndex}")
    @ResponseBody
    public Response page(@PathVariable Integer pageIndex){
        return Response.success(indexService.page(null, pageIndex));
    }

    @GetMapping("/article/{pageIndex}/{tag}")
    @ResponseBody
    public Response page(@PathVariable Integer pageIndex, @PathVariable String tag){
        return Response.success(indexService.page(tag, pageIndex));
    }
}
