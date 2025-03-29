package Buoi_5.Bai9;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class UpdateXML {
    private static final String FileName = "src/Buoi_5/Bai9/Update.xml";

    public static void main(String[] args) {
        try {
            File file = new File(FileName);
            if (!file.exists()) {
                System.out.println("File XML không tồn tại!");
                return;
            }

            // Đọc file XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            // Nhập ID sinh viên cần cập nhật
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập ID sinh viên cần cập nhật: ");
            String idUpdate = sc.nextLine();

            // Tìm sinh viên theo ID
            NodeList studentList = doc.getElementsByTagName("student");
            boolean found = false;

            for (int i = 0; i < studentList.getLength(); i++) {
                Node student = studentList.item(i);
                if (student.getNodeType() == Node.ELEMENT_NODE) {
                    Element studentElement = (Element) student;
                    String id = studentElement.getAttribute("id");

                    if (id.equals(idUpdate)) {
                        found = true;


                        System.out.print("Nhập tên mới: ");
                        String newName = sc.nextLine();
                        System.out.print("Nhập mã sinh viên mới: ");
                        String newMsv = sc.nextLine();
                        System.out.print("Nhập lớp mới: ");
                        String newClass = sc.nextLine();


                        studentElement.getElementsByTagName("name").item(0).setTextContent(newName);
                        studentElement.getElementsByTagName("msv").item(0).setTextContent(newMsv);
                        studentElement.getElementsByTagName("class").item(0).setTextContent(newClass);


                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                        DOMSource source = new DOMSource(doc);
                        StreamResult result = new StreamResult(new File(FileName));
                        transformer.transform(source, result);

                        System.out.println(" Đã cập nhật sinh viên có ID: " + idUpdate);
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println(" Không tìm thấy sinh viên có ID: " + idUpdate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
