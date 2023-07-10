package pageObjects;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Demo_comparsion {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {

        String path = "C:\\Users\\SadiqShaik\\Downloads\\BDDJavaFramework-maine\\BDDJavaFramework-maine\\BDDJavaFramework-main - Copy\\BDDJavaFramework-main\\src\\main\\resources\\WorkBook\\API\\TCReq_2.xml";
        String request1 = new String(Files.readAllBytes(Paths.get(path)));

        String path1 = "C:\\Users\\SadiqShaik\\Downloads\\BDDJavaFramework-maine\\BDDJavaFramework-maine\\BDDJavaFramework-main - Copy\\BDDJavaFramework-main\\src\\main\\resources\\WorkBook\\API\\Response\\TCRes_2.xml";
        String request2 = new String(Files.readAllBytes(Paths.get(path1)));

        Demo_comparsion demo=new Demo_comparsion();
       String req2= demo.prettyFormat(request2,2);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc=builder.parse(new InputSource(new StringReader(request1)));

        DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder1 = factory1.newDocumentBuilder();
        Document doc1=builder1.parse(new InputSource(new StringReader(request2)));

        File file = new File("C:\\Users\\SadiqShaik\\Downloads\\BDDJavaFramework-maine\\BDDJavaFramework-maine\\BDDJavaFramework-main - Copy\\BDDJavaFramework-main\\src\\main\\resources\\WorkBook\\API\\Xpath\\TCReq_2_xpath.txt");
        Scanner scan=new Scanner(file);


        String[] arrreq = new String[]{"PriorTermAmount", "TestTermPremium", "TestNetPremium", "TestOnset", "TestOffset", "TestCommissionTermPremium", "TestCommissionNet", "TestCommissionOnset", "TestCommissionOffset"};
        String[] arrres = new String[]{"PriorTermAmount", "TermPremium", "NetChange", "Onset", "Offset", "Commission", "CommNetChange", "CommissionOnset", "CommissionOffset"};

        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();

        String name = "";
        String name1 = "";

        int lines=0;
        while (scan.hasNextLine())
        {
            String line=scan.nextLine();
            lines++;
           // System.out.println(line);
            for(int i=0;i< arrreq.length;i++){
            if(line.contains(arrreq[i])){
               String responsexpath=line.substring(46);

                XPathExpression expr = xpath.compile(line+"/text()");
                XPathExpression expr1 = xpath.compile("//*"+responsexpath.replace(arrreq[i],arrres[i])+"/text()");

//               System.out.println("Request: "+line+"/text()");
//                System.out.println("Response: //*"+responsexpath.replace(arrreq[i],arrres[i])+"/text()");
                name = (String) expr.evaluate(doc, XPathConstants.STRING);
                name1 = (String) expr1.evaluate(doc1, XPathConstants.STRING);


                if(name!="" && name1!=""){
                    System.out.println("Request attribute "+arrreq[i]+" : "+name);
                    System.out.println("Respons attribute "+arrres[i]+" : "+name1);
                    System.out.println("****************************************");
                    if(!name.equals(name1))
                    System.out.println("Not matched at"+line);

                }
            }
            }
        }






            }

    public  String prettyFormat(String input, int indent) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            throw new RuntimeException(e); // simple exception handling, please review it
        }
    }



        }





