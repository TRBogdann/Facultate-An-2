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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class XMLSerializer {
    Document document;
    public XMLSerializer() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.newDocument();
    }
    public void serialize(List<Cost> costuri)
    {
        Element root  = document.createElement("costuri");
        document.appendChild(root);
        Set<Integer> set  = new HashSet<>();
        for(Cost c:costuri)
        {
            set.add(c.getCod());
        }
        for(Integer i:set)
        {
            List<Cost> filtered =  costuri.stream().filter(c->c.getCod()==i).toList();
            Element rootChild = document.createElement("cost");
            for(Cost c:filtered) {
                Element childChild = document.createElement("proiect");
                childChild.setAttribute("Cod_proiect",Integer.toString(c.getProiect()));
                childChild.setAttribute("Valoare_proiect",Double.toString(c.getPret()*c.getCantitate()));
                rootChild.appendChild(childChild);
            }
            root.appendChild(rootChild);
        }
    }
    public void save(String filename) throws TransformerException, FileNotFoundException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        DOMSource sourse = new DOMSource(document);
        StreamResult result = new StreamResult(new FileOutputStream(filename));

        transformer.transform(sourse,result);
    }
}
