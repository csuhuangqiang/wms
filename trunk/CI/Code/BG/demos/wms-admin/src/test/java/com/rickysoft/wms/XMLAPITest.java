package com.rickysoft.wms;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

public class XMLAPITest {
    @Test
    public void testRead() throws Exception {
        InputStream inputStream=getClass().getResourceAsStream("/wms.xml");
        Document document=new SAXReader().read(inputStream);
        List<Element> elements=document.selectNodes("/wms/setting");
        for (Element element:elements
             ) {
            System.out.println(element.attributeValue("name")+"="+element.attributeValue("value"));
        }
    }

    @Test
    public void testWrite() throws Exception{
        Setting setting=new Setting();
        setting.setSiteName("网易");
        setting.setSiteUrl("http:www.163.com");

        InputStream inputStream=getClass().getResourceAsStream("/app.xml");
        Document document=new SAXReader().read(inputStream);
        List<Element> elements=document.selectNodes("/app/setting");
        for (Element element:elements
                ) {
            String name=element.attributeValue("name");
            String value= BeanUtils.getProperty(setting,name);
            Attribute attribute=element.attribute("value");
            attribute.setValue(value);
        }

        OutputFormat outputFormat=OutputFormat.createPrettyPrint();
        outputFormat.setEncoding("UTF-8");
        outputFormat.setIndent(true);
        outputFormat.setIndent("	");
        outputFormat.setNewlines(true);

        XMLWriter xmlWriter=null;
        xmlWriter=new XMLWriter(new FileOutputStream(getClass().getResource("/app.xml").getPath()),outputFormat);
        xmlWriter.write(document);
        xmlWriter.flush();
    }
}
