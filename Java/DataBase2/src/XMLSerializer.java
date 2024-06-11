import org.w3c.dom.Document;

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
        this.document = builder.newDocument();

    }
    public void serialize(List<MateriiPrime> list)
    {
        var root = document.createElement("materii_prime");
        document.appendChild(root);
        for(MateriiPrime m:list)
        {
            var element = document.createElement("materie_prima");
            element.setAttribute("cod",Integer.toString(m.getCod()));
            element.setAttribute("denumire",m.getDenumire());
            element.setAttribute("valoare", Float.toString(m.getCantitate()*m.getPret()));
            root.appendChild(element);
        }
    }
    public void saveFile(String path) throws TransformerException {

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        DOMSource source = new DOMSource(document);

        StreamResult result = new StreamResult(path);
        transformer.transform(source,result);

    }
}
