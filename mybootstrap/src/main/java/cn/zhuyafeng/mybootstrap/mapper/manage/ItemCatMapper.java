package cn.zhuyafeng.mybootstrap.mapper.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhuyafeng.mybootstrap.pojo.ItemCat;



public interface ItemCatMapper<T> {

    public List<T> queryItemCatParent();

    public List<ItemCat> queryItemCatChild(@Param("pid")Long pid);

    public List<ItemCat> queryItemCatAll();

    public List<ItemCat> queryItemCatById(@Param("pid")Long pid);
}
