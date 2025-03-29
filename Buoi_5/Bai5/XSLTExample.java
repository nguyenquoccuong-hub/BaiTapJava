package Buoi_5.Bai5;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XSLTExample {
    public static void main(String[] args) {
        try{
            TransformerFactory factory = TransformerFactory.newDefaultInstance();
            Source xslt = new StreamSource(new File("src/Buoi_5/Bai5/products.xsl"));
            Transformer transformer = factory.newTransformer(xslt);

            Source xml = new StreamSource(new File("src/Buoi_5/Bai5/products.xml"));

            transformer.transform(xml, new StreamResult(new File("src/Buoi_5/Bai5/output.html")));
            System.out.println("Transformation completed.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
