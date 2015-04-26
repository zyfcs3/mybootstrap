package cn.zhuyafeng.mybootstrap.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.zhuyafeng.mybootstrap.pojo.Item;
import cn.zhuyafeng.mybootstrap.service.manage.ItemService;

@RequestMapping("item")
@Controller
@SuppressWarnings("deprecation")
public class ItemController {
    
    @Autowired
    private ItemService itemService;
    
    private final static Logger Logger = LoggerFactory.getLogger(ItemController.class);
    
    @RequestMapping("itemlist")
    public ModelAndView queryItemList(){
        ModelAndView mv = new ModelAndView("index");
        List<Item> items = itemService.queryItemList();
        mv.addObject("items", items);
        return mv;
    }
    
    @RequestMapping("itemsave")
    public String insertItem(Item item ,@RequestParam MultipartFile itemimg,HttpServletRequest request) throws Exception{
        try {
            System.out.println(request.getRealPath(""));
                if(null == item ){
                    throw new Exception("未填写商品");
                }
                if(itemimg.isEmpty()){  
                    System.out.println("图片未上传");  
                }else{  
                   String filename =  System.currentTimeMillis()+itemimg.getOriginalFilename();
                    //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中  
                    String realPath = request.getSession().getServletContext().getRealPath("/upload");
                   
                    
                    //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
                    FileUtils.copyInputStreamToFile(itemimg.getInputStream(), new File(realPath,filename));  
                    filename = "http://manage.taotao.com/upload/"+filename; 
                    item.setImage(filename);
                   
                    itemService.insertItem(item);
                }  
            
        } catch (IOException e) {
            Logger.error("文件上传错误"+e);
        }  
        
        
        return "redirect:/views/item/itemlist";
    }
    
    
    @RequestMapping("queryPageItem")
    @ResponseBody
    public Map<String,Object> getPageItem(){
        Map<String,Object>  pageItem  = itemService.queryPageItem();
        
        return pageItem;
    }
    @RequestMapping("queryLikeItem")
    @ResponseBody
    public Map<String,Object> queryLikeItem(@RequestParam("name")String name){
                try {
                    String str = new String(name.getBytes("ISO-8859-1"), "UTF-8");
                    
         Map<String,Object>  pageItem  = itemService.queryLikeItem(str);
         return pageItem;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
        return null;
    }
    
    @RequestMapping("queryItem")
    @ResponseBody
    public Item queryItemById(@RequestParam("id")Long id){
    Item item =	itemService.queryItemById(id);
    	
    	return item;
    }
    @RequestMapping("updateItem")
    public String updateItem(Item item){
    	itemService.updateItem(item);
    	
    	return "redirect:/views/item/itemlist";
    }

}
