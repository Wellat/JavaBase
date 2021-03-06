package hemi.xmu.learn.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by Hemi on 2017/10/19.
 */
public class XmlReaderByDOM {
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        try{
            File f = new File("resource/testXml.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);
            NodeList node = doc.getElementsByTagName("VALUE");
            for (int i = 0; i < node.getLength(); i++) {
                System.out.print("车牌号码:" + doc.getElementsByTagName("NO").item(i).getFirstChild().getNodeValue());
                System.out.println(" 车主地址:" + doc.getElementsByTagName("ADDR").item(i).getFirstChild().getNodeValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("运行时间："+(System.currentTimeMillis() - begin)+" 毫秒");
    }
}
