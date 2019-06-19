package studio.greeks.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/20 0:36
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/login")
    public String login(Model model){
        return "admin/login";
    }
}
