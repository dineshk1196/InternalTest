package pageObjects;

//import static org.apache.commons.lang3.time.DurationFormatUtils.s;

import com.codoid.products.fillo.Fillo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import utils.TestRunConfig;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Testing {

    static String path=TestRunConfig.APIPath;

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, TransformerException {
//        String[] lineAdd=new String[]{"testbed","CO042820sadiq"};
//
//        String op1               = System.getProperty("user.dir") + "/src/main/resources/WorkBook/API/"+requestfilename;
//        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
//        domFactory.setIgnoringComments(true);
//        DocumentBuilder builder = domFactory.newDocumentBuilder();
//        Document doc = builder.parse(new File(op1));
//
//        NodeList nodes = doc.getElementsByTagName("ratingWorkSheet");
//
//        Text a = doc.createTextNode(lineAdd[1]);
//        Element p = doc.createElement(lineAdd[0]);
//        p.appendChild(a);
//        nodes.item(0).getParentNode().insertBefore(p, nodes.item(0));
//
//        Transformer transformer = TransformerFactory.newInstance().newTransformer();
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//
//        StreamResult result = new StreamResult(new StringWriter());
//        DOMSource source = new DOMSource(doc);
//        transformer.transform(source, result);
//
//        String xmlOutput = result.getWriter().toString();
//        FileOutputStream output1 = new FileOutputStream(op1);
//        Files.writeString(Path.of(op1), xmlOutput, StandardCharsets.UTF_8);


          System.out.println(TestRunConfig.APIPath);

//        String pathApi= TestRunConfig.APIPath+"//sadiq.txt";
//        String xmlOutput = "sadiq";
//        FileOutputStream output1 = new FileOutputStream(pathApi);
//        Files.writeString(Path.of(pathApi), xmlOutput, StandardCharsets.UTF_8);

    }
}
