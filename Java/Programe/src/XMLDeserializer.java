import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLDeserializer {
    Document document;
    public XMLDeserializer(String filename) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(new File(filename));
    }

    public List<Program> deserialize()
    {
        List<Program> list = new ArrayList<>();
        try {
            Element root = document.getDocumentElement();
            NodeList arr = document.getElementsByTagName("program");
            for (int i = 0; i < arr.getLength(); i++)
            {
                Element node = (Element)arr.item(i);
                Program p =  new Program(
                        Integer.parseInt(node.getElementsByTagName("codProgram").item(0).getTextContent()),
                        node.getElementsByTagName("denumire").item(0).getTextContent(),
                        Integer.parseInt(node.getElementsByTagName("nrLocuri").item(0).getTextContent())
                );
                list.add(p);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return list;
    }

}
