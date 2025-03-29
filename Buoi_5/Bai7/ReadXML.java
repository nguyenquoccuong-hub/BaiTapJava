package Buoi_5.Bai7;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class ReadXML {
    private static final String FileName = "src/Buoi_5/Bai7/staff.xml";

    public static void main(String[] args) {
        try{
            File file = new File(FileName);
            if(!file.exists()){
                System.out.println("File XMl không tồn tại");
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList employeeList = doc.getElementsByTagName("employee");
            for (int i=0; i< employeeList.getLength(); i++){
                Node empNode = employeeList.item(i);
                if(empNode.getNodeType() == Node.ELEMENT_NODE){
                    Element empElement = (Element) empNode;
                    String id = empElement.getAttribute("id");
                    String name = empElement.getElementsByTagName("name").item(0).getTextContent();
                    String email = "N/A";
                    String phone = "N/A";

                    NodeList emailList = empElement.getElementsByTagName("email");
                    if (emailList.getLength() > 0) {
                        email = emailList.item(0).getTextContent();
                    }

                    NodeList phoneList = empElement.getElementsByTagName("phone");
                    if (phoneList.getLength() > 0) {
                        phone = phoneList.item(0).getTextContent();
                    }


                    String deptName = "N/A", location = "N/A";
                    Element department = (Element) empElement.getElementsByTagName("department").item(0);
                    if (department != null) {
                        NodeList deptNameList = department.getElementsByTagName("name");
                        if (deptNameList.getLength() > 0) {
                            deptName = deptNameList.item(0).getTextContent();
                        }

                        NodeList locationList = department.getElementsByTagName("location");
                        if (locationList.getLength() > 0) {
                            location = locationList.item(0).getTextContent();
                        }
                    } else {
                        System.out.println(" Không tìm thấy department cho nhân viên ID: " + id);
                    }

                    // In thông tin nhân viên
                    System.out.println("Employee ID : " + id);
                    System.out.println("Name       : " + name);
                    System.out.println("Email      : " + email);
                    System.out.println("Phone      : " + phone);
                    System.out.println("Department : " + deptName);
                    System.out.println("Location   : " + location);
                    System.out.println("    -----------------------    ");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
