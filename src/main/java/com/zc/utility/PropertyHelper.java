/**
 * @title PropertyHelper.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年4月28日 下午6:19:20
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHelper {
    public String getPropValue(String propFilePath, String propKey) {
        String result = "";
        Properties prop = new Properties();
        try {

            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream(propFilePath);
            prop.load(inputStream);
            // prop.load(new FileInputStream(propFilePath));
            result = prop.getProperty(propKey);
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // String propFileName = "config.properties";
        return result;
    }

    public static String getValue(String propFilePath, String propKey) {
        String result = "";
        Properties prop = new Properties();
        try {
            InputStream inputStream = PropertyHelper.class.getClassLoader()
                    .getResourceAsStream(propFilePath);
            prop.load(inputStream);
            result = prop.getProperty(propKey);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 获取属性文件对象
     * 
     * @param path
     *            资源文件路径
     * @return
     * @创建人 xyzhuzhou @创建时间 2016年7月14日 下午5:36:38
     */
    public static Properties loadProperties(String path) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = PropertyHelper.class.getClassLoader()
                    .getResourceAsStream(path);
            properties.load(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}
