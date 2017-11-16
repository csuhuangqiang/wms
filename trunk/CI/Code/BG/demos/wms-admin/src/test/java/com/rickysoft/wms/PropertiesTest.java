package com.rickysoft.wms;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesTest {

    @Test
    public void testRead() throws IOException {
        Properties properties=new Properties();

        // 获取文件流
        InputStream inputStream=getClass().getResourceAsStream("/app.properties");
        properties.load(new InputStreamReader(inputStream, "UTF-8"));

        // 输出
        System.out.println(properties.getProperty("siteName"));
    }
}
