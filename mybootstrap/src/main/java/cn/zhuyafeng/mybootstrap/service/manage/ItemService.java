package cn.zhuyafeng.mybootstrap.service.manage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhuyafeng.mybootstrap.mapper.manage.ItemMapper;
import cn.zhuyafeng.mybootstrap.pojo.Item;

@Service
public class ItemService {
    
    @Autowired
    private ItemMapper itemMapper;
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private final static Logger logger = LoggerFactory.getLogger(ItemService.class);

    public List<Item> queryItemList() {
        List<Item> items = itemMapper.select(null);
        
        return items;
        
    }

    public void insertItem(Item item) {
        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        itemMapper.insert(item);
    }

    public Map<String, Object> queryPageItem() {
        Map<String, Object>pageItem = new HashMap<String, Object>();
        Item item = new Item ();
        item.setCid(904l);
        List<Item> itemAll = itemMapper.select(null);
        List<Item> imgItem = itemMapper.select(item);
        pageItem.put("itemAll", itemAll);
        pageItem.put("imgItems", imgItem);
        return pageItem;
    }

    public Map<String, Object> queryLikeItem(String name) {
        Map<String, Object>likeItem = new HashMap<String, Object>();
        List<Item> item = itemMapper.queryLikeItem("%"+name+"%");
        likeItem.put("item", item);
        return likeItem;
    }

	public Item queryItemById(Long id) {
		
		return itemMapper.selectByPrimaryKey(id);
	}

	public void updateItem(Item item) {
		
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		itemMapper.updateByPrimaryKey(item);
		try {
			sendMq("update",item.getId());
			
		} catch (AmqpException e) {
			// TODO Auto-generated catch block
			logger.error("发生update消息出错",e);
			for(int i = 1 ; i<4;i++){
				try {
					
						sendMq("update",item.getId());
					
				} catch (Exception e1) {
					logger.error("第"+i+"次发生update消息出错"+item.getId(),e);
				} 	
			}
		}
		
	}
	
	public void sendMq(String key,Long id) {
	    
		rabbitTemplate.setRoutingKey(key);
		rabbitTemplate.convertAndSend(id);
	}
    
    

}
