package Buoi_5.Bai8;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class DeleteXML {
    private static final String FileName = "src/Buoi_5/Bai8/DeleteXML.xml";

    public static void main(String[] args) {
        try{
            File file = new File(FileName);
            if(!file.exists()){
                System.out.println("File XML không tồn tại");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            Scanner sc = new Scanner(System.in);
            System.out.println("Nhập id sinh viên cần xoá : ");
            String idDelete = sc.nextLine();

            NodeList studentList = doc.getElementsByTagName("student");
            boolean found = false;

            for (int i=0; i< studentList.getLength(); i++){
                Node student = studentList.item(i);
                if (student.getNodeType() == Node.ELEMENT_NODE) {
                    Element studentElement = (Element) student;
                    String id = studentElement.getAttribute("id");

                    if (id.equals(idDelete)) {
                        student.getParentNode().removeChild(student);
                        found = true;
                        break;  // Dừng lại sau khi tìm thấy
                    }
                }
            }

            if (found) {
                // Ghi lại XML sau khi xóa
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(FileName));
                transformer.transform(source, result);

                System.out.println("Đã xóa sinh viên có ID: " + idDelete);
            } else {
                System.out.println(" Không tìm thấy sinh viên có ID: " + idDelete);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
