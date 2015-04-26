package cn.zhuyafeng.mybootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("page")
@Controller
public class PageController {

    @RequestMapping("{path}")
    public String goPage(@PathVariable("path")String path){
       
        
        return path;
    }
    
    
}
