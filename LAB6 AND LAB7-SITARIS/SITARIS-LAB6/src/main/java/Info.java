import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pak.Book;

import java.util.ArrayList;

import java.io.File;

@WebServlet("/temp-serv")
public class Info extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = new File("C:\\Users\\keerd\\IdeaProjects\\SITARIS4\\src\\library.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document document = null;
            document = builder.parse(file);
            ArrayList<Book> book = new ArrayList<>();
            Element catalogNode = document.getDocumentElement();
            NodeList bookList = catalogNode.getElementsByTagName("book");
            for (int i = 0; i < bookList.getLength(); i++) {
                Element bookElement = (Element) bookList.item(i);
                String author = bookElement.getElementsByTagName("author").item(0).getTextContent();
                String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
                String genre = bookElement.getElementsByTagName("genre").item(0).getTextContent();
                String price = bookElement.getElementsByTagName("price").item(0).getTextContent();
                float price_float = Float.parseFloat(price);
                String publish_date = bookElement.getElementsByTagName("publish_date").item(0).getTextContent();
                Book book_obj = new Book(author, title, genre, price_float, publish_date);
                book.add(book_obj);
            }
            System.out.println(book.toString());
            req.setAttribute("book", book);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
