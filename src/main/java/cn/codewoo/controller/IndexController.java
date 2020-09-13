package cn.codewoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kehong
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @GetMapping("/404")
    public String error404(){
        return "/error/404";
    }
    @GetMapping("/403")
    public String error403(){
        return "/error/403";
    }
    @GetMapping("/500")
    public String error500(){
        return "/error/500";
    }
    @GetMapping("/login")
    public String logout(){
        return "login";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("main")
    public String main(){
        return "main";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/menus")
    public String menu(){
        return "/menus/menu";
    }
}
