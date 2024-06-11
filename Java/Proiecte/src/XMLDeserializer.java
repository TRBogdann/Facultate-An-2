import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.plaf.synth.SynthTextAreaUI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XMLDeserializer {
    Document document;
    public XMLDeserializer(String filename) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(new FileInputStream(filename));
        document.getDocumentElement().normalize();
    }

    public List<Cost> deserialize()
    {
        List<Cost> list = new ArrayList<>();
        try{
            Element root = document.getDocumentElement();
            NodeList arr = document.getElementsByTagName("cost");
            for(int i=0;i<arr.getLength();i++)
            {
                Element obj = (Element)arr.item(i);

                Cost c = new Cost(
                        Integer.parseInt(obj.getElementsByTagName("cod_cost").item(0).getTextContent()),
                        Integer.parseInt(obj.getElementsByTagName("cod_proiect").item(0).getTextContent()),
                        obj.getElementsByTagName("denumire_cost").item(0).getTextContent(),
                        obj.getElementsByTagName("um").item(0).getTextContent(),
                        Float.parseFloat(obj.getElementsByTagName("cantitate").item(0).getTextContent()),
                        Float.parseFloat(obj.getElementsByTagName("pu").item(0).getTextContent())
                );
                list.add(c);


            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
