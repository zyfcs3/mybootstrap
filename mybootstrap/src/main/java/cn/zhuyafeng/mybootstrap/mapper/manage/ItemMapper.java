package cn.zhuyafeng.mybootstrap.mapper.manage;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.zhuyafeng.mybootstrap.pojo.Item;

import com.github.abel533.mapper.Mapper;

public interface ItemMapper extends Mapper<Item> {

   /*public List<Item> select();

   public void insert(Item item);

   public List<Item> selectByCid(@Param("cid")long cid);*/
    
    @Select(value="select * from tb_item where title like #{name} LIMIT 0,100")
   public List<Item> queryLikeItem(String name);

}
