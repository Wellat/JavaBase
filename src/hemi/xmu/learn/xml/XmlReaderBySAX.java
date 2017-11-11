package hemi.xmu.learn.xml;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Stack;

/**
 * Created by Hemi on 2017/10/19.
 */
public class XmlReaderBySAX extends DefaultHandler {
    Stack tags = new Stack();

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XmlReaderBySAX reader = new XmlReaderBySAX();
            parser.parse(new InputSource("resource/testXml.xml"),reader);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("运行时间："+(System.currentTimeMillis() - begin)+" 毫秒");
    }

    public void characters(char[] ch,int start,int length) throws SAXException{
        String tag = (String) tags.peek();
        if (tag.equals("NO")) {
            System.out.print("车牌号码：" + new String(ch, start, length));
        }
        if (tag.equals("ADDR")) {
            System.out.println(" 地址:" + new String(ch, start, length));
        }
    }

    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attrs) {
        tags.push(qName);
    }
}
