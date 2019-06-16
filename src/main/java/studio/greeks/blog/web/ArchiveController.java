package studio.greeks.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import studio.greeks.blog.service.IndexService;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/16 23:35
 */
@Controller
public class ArchiveController {
    @Autowired
    private IndexService indexService;

    @RequestMapping({"/archive","/archive.html","/archive.htm"})
    public String index(Model model){
        model.addAttribute("fragment", indexService.getFragmentVo("归档", "archive"));
        return "archive";
    }
}
