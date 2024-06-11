import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLDeserializer {
    Document document;
    public XMLDeserializer(String filename) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(new FileInputStream(filename));

    }
    public List<Masina> deserialize()
    {
        List<Masina> list = new ArrayList<>();
        Node root = document.getElementsByTagName("Masini").item(0);
        NodeList elemets = root.getChildNodes();
        for(int i=0;i<elemets.getLength();i++)
        {
            Node element = elemets.item(i);
            var atrib = element.getAttributes();
            Masina m = new Masina();
            m.setSerie(Integer.parseInt(atrib.getNamedItem("serie").getNodeValue()));
            m.setCost(Float.parseFloat(atrib.getNamedItem("cost").getNodeValue()));
            m.setNume(atrib.getNamedItem("nume").getNodeValue());
            m.setNumar(atrib.getNamedItem("numar").getNodeValue());
            list.add(m);
        }
        return  list;
    }
}
