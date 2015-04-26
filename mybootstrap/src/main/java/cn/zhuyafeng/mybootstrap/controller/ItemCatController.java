package cn.zhuyafeng.mybootstrap.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhuyafeng.mybootstrap.pojo.ItemCat;
import cn.zhuyafeng.mybootstrap.service.manage.ItemCatService;

@Controller
@RequestMapping("itemCat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;
    
    
    @RequestMapping("itemCatParent")
    @ResponseBody
    public List<ItemCat> queryItemCatParent(){
        List<ItemCat> itemCats = itemCatService.queryItemCatParent();
        
        return itemCats;
    }
    @RequestMapping("itemCatChild")
    @ResponseBody
    public List<ItemCat> queryItemCatChild(@RequestParam("pid")Long pid){
        List<ItemCat> itemCats = itemCatService.queryItemCatChild(pid);
        return itemCats;
    }
    
    @RequestMapping("itemCatAll")
    @ResponseBody
    public  Map<String,String> queryItemCatAll(){
        Map<String,String> itemCats = itemCatService.queryItemCatAll();
        return itemCats;
    }
    
}
