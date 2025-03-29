package Buoi_5.Bai6;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class StudentXML {
    private static final String FileName = "src/Buoi_5/Bai6/students.xml";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập ID sinh viên : ");
        String id = sc.nextLine();
        System.out.println("Nhập tên sinh viên : ");
        String name = sc.nextLine();
        System.out.println("Nhập tuổi : ");
        String age = sc.nextLine();
        System.out.println("Nhập chuyên ngành : ");
        String major = sc.nextLine();

        addStudent(id,name,age,major);
        System.out.println("Sinh viên đã được thêm vào");


    }

    public static void addStudent(String id, String name, String age, String major){
        try{
            File file = new File(FileName);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc;

            Element root;
            if(file.exists()){
                //nếu file đã tồn tại -> đọc file
                doc = builder.parse(file);

                root = doc.getDocumentElement();
            }else {
                //nếu file chưa tồn taị -> tạo mới
                doc = builder.newDocument();
                root = doc.createElement("students");
                doc.appendChild(root);
            }

            //tạo phần tử sinh viên mới
            Element student = doc.createElement("student");

            Element idElement = doc.createElement("id");
            idElement.appendChild(doc.createTextNode(id));
            student.appendChild(idElement);

            Element nameElement = doc.createElement("name");
            nameElement.appendChild(doc.createTextNode(name));
            student.appendChild(nameElement);

            Element ageElement = doc.createElement("age");
            ageElement.appendChild(doc.createTextNode(age));
            student.appendChild(ageElement);

            Element majorElement = doc.createElement("major");
            majorElement.appendChild(doc.createTextNode(major));
            student.appendChild(majorElement);

            root.appendChild(student);

            //ghi nhớ dữ liệu ra file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("Dữ liệu đã được ghi thành công vào file XML!");





        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
