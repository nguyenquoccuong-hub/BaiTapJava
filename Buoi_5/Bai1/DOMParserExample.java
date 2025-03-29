package Buoi_5.Bai1;

import org.w3c.dom.NodeList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DOMParserExample {
    public static void main(String[] args) {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = (Document) builder.parse("src/Buoi_5/Bai1/products.xml");

            NodeList productList = document.getElementsByTagName("product");

            for (int i=0; i< productList.getLength(); i++){
                Element product = (Element) productList.item(i);
                String name = product.getElementsByTagName("name").item(0).getTextContent();
                String price = product.getElementsByTagName("price").item(0).getTextContent();
                String adress = product.getElementsByTagName("adress").item(0).getTextContent();
                System.out.println("Product : "+name+", price : "+price+", adress : "+adress);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
