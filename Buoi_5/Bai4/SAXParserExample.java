package Buoi_5.Bai4;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXParserExample {
    public static void main(String[] args) {
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler(){
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
                    System.out.println("Start Element : "+qName);
                }

                public void characters(char ch[], int start, int length) throws SAXException{
                    System.out.println("Characters : "+new String(ch, start,length));
                }
            };

            saxParser.parse("src/Buoi_5/Bai1/products.xml", handler);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
