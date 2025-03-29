package Buoi_5.GUI;

import org.w3c.dom.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.*;
import java.io.File;


public class LibaryManager {
    private static final String FILE_NAME = "src/Buoi_5/GUI/Libary.xml";
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtID, txtTitle, txtAuthor, txtYear, txtPublisher, txtPages, txtGenre, txtPrice;

    public LibaryManager() {
        frame = new JFrame("Library Management");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        // Table
        model = new DefaultTableModel(new String[]{"ID", "Title", "Author", "Year", "Publisher", "Pages", "Genre", "Price"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Form Panel
        JPanel panel = new JPanel(new GridLayout(9, 2));
        panel.add(new JLabel("ID:")); txtID = new JTextField(); panel.add(txtID);
        panel.add(new JLabel("Title:")); txtTitle = new JTextField(); panel.add(txtTitle);
        panel.add(new JLabel("Author:")); txtAuthor = new JTextField(); panel.add(txtAuthor);
        panel.add(new JLabel("Year:")); txtYear = new JTextField(); panel.add(txtYear);
        panel.add(new JLabel("Publisher:")); txtPublisher = new JTextField(); panel.add(txtPublisher);
        panel.add(new JLabel("Pages:")); txtPages = new JTextField(); panel.add(txtPages);
        panel.add(new JLabel("Genre:")); txtGenre = new JTextField(); panel.add(txtGenre);
        panel.add(new JLabel("Price:")); txtPrice = new JTextField(); panel.add(txtPrice);
        frame.add(panel, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnLoad = new JButton("Load");
        JButton btnReset = new JButton("Reset");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnLoad);
        buttonPanel.add(btnReset);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Event Listeners
        btnAdd.addActionListener(e -> addBook());
        btnUpdate.addActionListener(e -> updateBook());
        btnDelete.addActionListener(e -> deleteBook());
        btnLoad.addActionListener(e -> loadBooks());
        btnReset.addActionListener(e -> resetFields());

        loadBooks();
        frame.setVisible(true);
    }

    private void loadBooks() {
        model.setRowCount(0);
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return;

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("book");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                model.addRow(new Object[]{
                        element.getAttribute("id"),
                        element.getElementsByTagName("title").item(0).getTextContent(),
                        element.getElementsByTagName("author").item(0).getTextContent(),
                        element.getElementsByTagName("year").item(0).getTextContent(),
                        element.getElementsByTagName("publisher").item(0).getTextContent(),
                        element.getElementsByTagName("pages").item(0).getTextContent(),
                        element.getElementsByTagName("genre").item(0).getTextContent(),
                        element.getElementsByTagName("price").item(0).getTextContent()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addBook() {
        String[] bookData = {txtID.getText(), txtTitle.getText(), txtAuthor.getText(), txtYear.getText(), txtPublisher.getText(), txtPages.getText(), txtGenre.getText(), txtPrice.getText()};
        model.addRow(bookData);
        saveBooks();
    }

    private void updateBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String[] bookData = {txtID.getText(), txtTitle.getText(), txtAuthor.getText(), txtYear.getText(), txtPublisher.getText(), txtPages.getText(), txtGenre.getText(), txtPrice.getText()};
            for (int i = 0; i < bookData.length; i++) {
                model.setValueAt(bookData[i], selectedRow, i);
            }
            saveBooks();
        }
    }

    private void deleteBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
            saveBooks();
        }
    }

    private void resetFields() {
        txtID.setText("");
        txtTitle.setText("");
        txtAuthor.setText("");
        txtYear.setText("");
        txtPublisher.setText("");
        txtPages.setText("");
        txtGenre.setText("");
        txtPrice.setText("");
    }

    private void saveBooks() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("books");
            doc.appendChild(root);

            for (int i = 0; i < model.getRowCount(); i++) {
                Element book = doc.createElement("book");
                book.setAttribute("id", model.getValueAt(i, 0).toString());

                String[] tags = {"title", "author", "year", "publisher", "pages", "genre", "price"};
                for (int j = 1; j < tags.length + 1; j++) {
                    Element element = doc.createElement(tags[j - 1]);
                    element.appendChild(doc.createTextNode(model.getValueAt(i, j).toString()));
                    book.appendChild(element);
                }
                root.appendChild(book);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(FILE_NAME));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LibaryManager();
    }
}
