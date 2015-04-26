package cn.zhuyafeng.mybootstrap.utils;

import java.io.IOException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitConnection {
    
    
    public static Connection getConnection(){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        
        try {
            connectionFactory.setHost("localhost");
            connectionFactory.setVirtualHost("/taobao");
            connectionFactory.setUsername("taobao");
            connectionFactory.setPassword("taobao");
            Connection connection = connectionFactory.newConnection();
            return connection;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }

}
