package pageObjects;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.xpath.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.xml.sax.*;
import org.w3c.dom.*;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.xml.sax.SAXException;


import static org.jsoup.nodes.Document.OutputSettings.Syntax.xml;

class ReusableClassXml {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        String path = "C:\\Users\\SadiqShaik\\Downloads\\Excel_auto\\TCReq_2.xml";
        String request1 = new String(Files.readAllBytes(Paths.get(path)));

        String path1 = "C:\\Users\\SadiqShaik\\Downloads\\Excel_auto\\TCRes_2.xml";
        String request2 = new String(Files.readAllBytes(Paths.get(path1)));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder1 = factory.newDocumentBuilder();

        Document document = builder.parse(new InputSource(new StringReader(request1)));

        Document document1 = builder.parse(new InputSource(new StringReader(request2)));

        document.getDocumentElement().normalize();
        document1.getDocumentElement().normalize();

        NodeList PropertyTags = document.getElementsByTagName("Property");
        System.out.println(PropertyTags.getLength());

        NodeList PropertyTags1 = document1.getElementsByTagName("mdl:Property");
        System.out.println(PropertyTags1.getLength());
        String[] Array = new String[200];
        int n = 0;

        for (int i = 0; i < PropertyTags.getLength(); i++) {
            Node Property = PropertyTags.item(i);
            if (Property.getNodeType() == Node.ELEMENT_NODE) {
                Element Propertyelement = (Element) Property;
                NodeList Propertychild = Propertyelement.getChildNodes();
                for (int j = 0; j < Propertychild.getLength(); j++) {
                    int k = 0;
                    k = Propertychild.item(j).getChildNodes().getLength();
                    // System.out.println(k);
                    if (k > 4) {
                        NodeList Coveragechild = Propertychild.item(j).getChildNodes();
                        for (int m = 0; m < Coveragechild.getLength(); m++) {
                            if (Coveragechild.item(m).getNodeName().equals("Id")) {
                                Array[n] = Coveragechild.item(m).getTextContent();
                                n++;
                            }
                        }
                    }

                }
            }
        }
        int count = 0;
        for (int i = 0; i < Array.length; i++) {
            //System.out.println(Array[i]);
            count++;
            if (Array[i] == null)
                break;
        }

        String name = "";
        String name1 = "";
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();

        String[] arrreq = new String[]{"PriorTermAmount", "TestTermPremium", "TestNetPremium", "TestOnset", "TestOffset", "TestCommissionTermPremium", "TestCommissionNet", "TestCommissionOnset", "TestCommissionOffset"};
        String[] arrres = new String[]{"PriorTermAmount", "TermPremium", "NetChange", "Onset", "Offset", "Commission", "CommNetChange", "CommissionOnset", "CommissionOffset"};

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < arrreq.length; j++) {
                name=null;
                name1=null;
                XPathExpression expr = xpath.compile("//*[Id='" + Array[i] + "']/./"+arrreq[j]+"/text()");
                XPathExpression expr1 = xpath.compile("//*[Id='" + Array[i] + "']/./"+arrres[j]+"/text()");

                name = (String) expr.evaluate(document, XPathConstants.STRING);
                name1 = (String) expr1.evaluate(document1, XPathConstants.STRING);
                if(name!="" && name1!="") {
                    System.out.println("id comparing: "+Array[i]);
                    System.out.print("Attribute "+arrreq[j]+":"+name);
                    System.out.println(" *** Attribute "+arrres[j]+":"+name1);
                    if (!name.equals(name1))
                        System.out.println("******not Matched****");
                }

            }
        }
    }


}

