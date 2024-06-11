import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.List;

public class XMLSerializer {
    Document document;
    public XMLSerializer() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.newDocument();
    }

    public void serialize(List<Masina> list)
    {
        Element root = document.createElement("Masini");
        document.appendChild(root);
        for(Masina m:list)
        {
            Element rootChild = document.createElement("Masina");
            rootChild.setAttribute("serie",Integer.toString(m.getSerie()));
            rootChild.setAttribute("nume",m.getNume());
            rootChild.setAttribute("cost",Float.toString(m.getCost()));
            rootChild.setAttribute("numar",m.getNumar());
            root.appendChild(rootChild);
        }
    }

    public void save(String filepath) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        DOMSource source = new DOMSource(document);

        StreamResult result = new StreamResult(filepath);

        transformer.transform(source,result);
    }
}
