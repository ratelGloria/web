package com.two.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MySourcePool {


    /*
    * 连接池
    * */

    //读properties

    public Properties getProperties(){

        InputStream resourceAsStream = MySourcePool.class.getResourceAsStream("");

        Properties properties = new Properties();

        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }


    //创建连接
    public Connection getConnection(){

        DataSource dataSource = null;

        try {
             dataSource = DruidDataSourceFactory.createDataSource(getProperties());

             return dataSource.getConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }




    /*
    *创建连接池
    * */
        public MySourcePool(){

        ArrayList<Connection> connections = new ArrayList<>();

        for(int i = 0;i<10;i++){

            Connection connection = new MySourcePool().getConnection();
            connections.add(connection);
        }
    }

}
