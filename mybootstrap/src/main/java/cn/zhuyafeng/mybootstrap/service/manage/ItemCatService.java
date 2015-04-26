package cn.zhuyafeng.mybootstrap.service.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhuyafeng.mybootstrap.mapper.manage.ItemCatMapper;
import cn.zhuyafeng.mybootstrap.pojo.ItemCat;

@Service
public class ItemCatService {

    @Autowired
    private ItemCatMapper<ItemCat> itemCatMapper;
    
    public List<ItemCat> queryItemCatParent() {
        
        List<ItemCat> itemCats = itemCatMapper.queryItemCatParent();
        
        return itemCats;
    }

    public List<ItemCat> queryItemCatChild(Long pid) {
        // TODO Auto-generated method stub
        return itemCatMapper.queryItemCatChild(pid);
    }

    public  Map<String,String> queryItemCatAll() {
        // TODO Auto-generated method stub
        StringBuilder builder = new StringBuilder();
        List<ItemCat> itemCats =  itemCatMapper.queryItemCatAll();
        Map<String,String> map = new HashMap<String, String>();
        for (ItemCat itemCat : itemCats) {
            if(itemCat.getParentId()==0l){
                builder.append("<li class='nli'><a class='active'  href='#'>");
                builder.append(itemCat.getName()+"</a><div><ul>");
                Long pid = itemCat.getId();
                List<ItemCat>itemCatChilds = itemCatMapper.queryItemCatById(pid);
                for (ItemCat itemCatChild : itemCatChilds) {
                    
                 builder.append("<li><a class='active'  href='#'>"+itemCatChild.getName()+"</a></li>");    
                    
                }
              
                builder.append(" </ul></div></li>");
            }
        }
        String liItemCat = builder.toString();
        map.put("liItemCat", liItemCat);
        /*      <!--  <li><a class="active"  href="#">Home</a>
        <div>
          <ul>
            <li><a href="index2.html">Home Style 2</a>
            </li>
          </ul>
        </div>
      </li> --> */
        
        return map;
    }

    

}
