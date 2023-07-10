package runners;
import com.codoid.products.exception.FilloException;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonNode;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.node.ObjectNode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import pageObjects.Demo_comparsion;
import pageObjects.Jsontoxml;
import pageObjects.XpathGenerator;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.*;
import java.io.*;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import org.json.JSONObject;
import org.json.JSONArray;
import utils.TestRunConfig;


public class API_Operations extends MainRunner {

    //************************Variables*****************************************
    String requestbody = null;              String res  = null;
    String id1 = null;                      String id2   = null;
    String loc = null;                      int resultvalue;
    String[] results = new String[1500];    XpathGenerator gen;
    Jsontoxml JX;
    //***********************variables*****************************************
    public String getpolicynumber() {
        return loc;
    }


    public void apiactions(String Environment,        String verb,             String endpoint1,
                           String[] headder,          String RequestFileName,  String variables,
                           String ResponseFile,       String[] reqAttArray,    String[] resAttArray,
                           String[] Ignoredcoverages, String tc,               String pathf,
                           String[] AttributeValue,   String Token,            String Project,
                           String[] Expected, String[] addline) throws IOException, KeyManagementException, NoSuchAlgorithmException {

        String endpoint       = endpoint1;
        String path           = System.getProperty("user.dir") + "\\src\\main\\resources\\WorkBook\\API\\" + RequestFileName;

        if (endpoint.contains("%s"))
            endpoint1         = String.format(endpoint, loc);
            endpoint          = endpoint1;
            System.out.println(endpoint);

    //**********************HttpClient***********************************************************
        DefaultHttpClient httpClient = new DefaultHttpClient();
        SSLContext ssl_ctx           = SSLContext.getInstance("TLS");
        TrustManager[] certs         = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String t) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String t) {
            }
        }};

        ssl_ctx.init(null, certs, new SecureRandom());
        SSLSocketFactory ssf         = new SSLSocketFactory(ssl_ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        ClientConnectionManager ccm  = httpClient.getConnectionManager();
        SchemeRegistry sr            = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", 443, ssf));

        HttpPost httpPost            = new HttpPost(endpoint);
    //*************************HttpClient********************************************************

    //************************Header************************************************************
        for (int i = 0; i < headder.length; i++) {
            if (headder[i].contains("Token")) {
                httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + Token);
            }
            if (headder[i].contains("json") || headder[i].contains("xml"))
                httpPost.setHeader("Content-type", headder[i]);

        }
    //*******************************************************************************************

    //************************SetVariables*******************************************************
        String[] SetVariables               = variables.split(",");

        for (int i = 0; i < SetVariables.length; i++) {
            if (SetVariables[i].contains("Locator")) {
                System.out.println(loc);
                Set_Locator(loc, RequestFileName);
                Policynumber                = loc;

            }
        }
    //********************************************************************************************

        try {
            if (!RequestFileName.equals("No Body")) {

                requestbody                 = new String(Files.readAllBytes(Paths.get(path)));
                StringEntity stringEntity   = new StringEntity(requestbody);
                httpPost.getRequestLine();
                httpPost.setEntity(stringEntity);
            }

    //**************************Response*********************************************************
            HttpResponse response           = httpClient.execute(httpPost);
            BufferedReader br               = new BufferedReader(
                                              new InputStreamReader((response.getEntity().getContent())));

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            StringBuffer result              = new StringBuffer();
            String line                      = "";
            String ResponseString            = "";

            while ((line = br.readLine()) != null) {
                ResponseString               = String.valueOf(result.append(line));
            }

            FileOutputStream output          = new FileOutputStream(System.getProperty("user.dir") + "/src/main/resources/WorkBook/API/Response/" + ResponseFile);
            String op                        = System.getProperty("user.dir") + "/src/main/resources/WorkBook/API/Response/" + ResponseFile;
            Files.writeString(Path.of(op), ResponseString, StandardCharsets.UTF_8);

            System.out.println(ResponseString);
            res                              = ResponseString;


        //*********************************************************************************************

            if (Project.toUpperCase().contains("FAMI")) {

                gen                          = new XpathGenerator();
                gen.generatexpath(RequestFileName);
                comparexml(RequestFileName, ResponseFile, reqAttArray, resAttArray, Ignoredcoverages, tc, pathf,Project);

            }

            else {

                String xml                    =null;

                if(AttributeValue.length>1) {
                    //*****************************JSONTOXML**************************************************
                    JX                       = new Jsontoxml();
                    xml                      = JX.ConvJsontoXml(ResponseString);

                    FileOutputStream output1 = new FileOutputStream(System.getProperty("user.dir") + "/src/main/resources/WorkBook/API/" + ResponseFile.replace(".json", ".xml"));
                    String op1               = System.getProperty("user.dir") + "/src/main/resources/WorkBook/API/" + ResponseFile.replace(".json", ".xml");
                    Files.writeString(Path.of(op1), xml, StandardCharsets.UTF_8);
                    //****************************************************************************************

                    gen                      = new XpathGenerator();
                    gen.generatexpath(ResponseFile.replace(".json", ".xml"));
                    comparexml(ResponseFile.replace(".json",".xml"),"",AttributeValue,Expected,null,tc,pathf,Project);

                }
                       int c,d;
                for (int i = 0; i < AttributeValue.length; i++) {

                    if (AttributeValue[i].contains("locator")) {

                        loc = Create_locator(ResponseString, AttributeValue[i]);
                        if(loc.length()==9) {
                            Policynumber = loc;
                            System.out.println(Policynumber);
                        }



                    }

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

        public void comparexml (String reqdoc,         String resdoc,            String[]AttributeValue,
                                String[] ExpectedValue, String[]IgnoredCoverages, String Testcasename,
                                String PathExcelReport,String project) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, FilloException {

        //***************************************RequestXML************************************************************
        connection                     = fillo.getConnection(PathExcelReport + "ExcelTestReport.xlsx");
        String path                    = System.getProperty("user.dir") + "\\src\\main\\resources\\WorkBook\\API\\" + reqdoc;
        String request1                = new String(Files.readAllBytes(Paths.get(path)));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder        = factory.newDocumentBuilder();
        Document doc                   = builder.parse(new InputSource(new StringReader(request1)));
        Document doc1                  =null;
        //**************************************************************************************************************

        if(project.toUpperCase().equals("FAMI")) {

            //***************************************ResponseXMLFAMI*************************************************
            String path1                    = System.getProperty("user.dir") + "\\src\\main\\resources\\WorkBook\\API\\Response\\" + resdoc;
            String request2                 = new String(Files.readAllBytes(Paths.get(path1)));

            DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder1        = factory1.newDocumentBuilder();
            doc1                            = builder1.parse(new InputSource(new StringReader(request2)));
            //***********************************************************************************************************

        }

            String Xpathfilepath            = TestRunConfig.APIPath+ "\\Xpath\\" + reqdoc.replace(".xml", ".txt");
            File file                       = new File(Xpathfilepath);
            Scanner scan                    = new Scanner(file);

            XPathFactory xpathfactory       = XPathFactory.newInstance();
            XPath xpath                     = xpathfactory.newXPath();

            String ActualValue              = "";
            String ExpectedValues           = "";
            int resultvalue                 = 0;
            int lines                       = 0;
            String responsexpathfull        =null;
            String comparenum               =null;
            Arrays.fill(results, null);


            while (scan.hasNextLine()) {

                String line           = scan.nextLine();
                int flag              = 0;
                int total             = 0;
                lines++;

                for (int i = 0; i < AttributeValue.length; i++) {

                    if(!project.equals("FAMI"))
                        comparenum    = line.replaceAll("\\d","").replace("[]","");
                    else
                        comparenum    = line;


                    if (comparenum.endsWith(AttributeValue[i])) {

                        if(project.toUpperCase().equals("FAMI")) {

                            for (int j = 0; j < IgnoredCoverages.length; j++) {
                                if (line.contains(IgnoredCoverages[j]))
                                    flag              = 1;
                            }
                            if (flag == 0) {

                                String responsexpath  = line.substring(46);
                                XPathExpression expr  = xpath.compile(line + "/text()");
                                XPathExpression expr1 = xpath.compile("//*" + responsexpath.replace(AttributeValue[i], ExpectedValue[i]) + "/text()");
                                responsexpathfull     = "//*" + responsexpath.replace(AttributeValue[i], ExpectedValue[i]);
                                ActualValue           = (String) expr.evaluate(doc, XPathConstants.STRING);
                                ExpectedValues        = (String) expr1.evaluate(doc1, XPathConstants.STRING);

                                if (line.contains("Id")) {

                                    id1               = ActualValue;
                                    id2               = ExpectedValues;

                                }}
                        }

                        else {

                            flag                      = 1;
                            XPathExpression expr      = xpath.compile(line + "/text()");
                            ActualValue               = (String) expr.evaluate(doc, XPathConstants.STRING);

                            System.out.println(ActualValue);

                            if (line.contains("name")) {
                                for (int j = 0; j < ExpectedValue.length; j++) {

                                    String[] Expectedcoveragedata = ExpectedValue[j].split("\\|");

                                    if (ActualValue.contains(Expectedcoveragedata[0])) {

                                        id2                       = Expectedcoveragedata[0];
                                        ExpectedValues            = Expectedcoveragedata[0];
                                        id1                       = ActualValue;

                                    }}
                            }

                            else {

                                for (int j = 0; j < ExpectedValue.length; j++) {

                                    String[] Expectedcoveragedata = ExpectedValue[j].split("\\|");

                                    if (ExpectedValues.contains(Expectedcoveragedata[0])) {

                                        ExpectedValues            = Expectedcoveragedata[1];
                                        total = 1;

                                    }}
                            }

                        }
                            if(flag==0||total==1) {

                                if (ActualValue != "" || ExpectedValues != "") {

                                    if (!ActualValue.equals(ExpectedValues)) {

                                        if(!project.toUpperCase().equals("FAMI"))
                                            results[resultvalue] = "<b>Names of coverages are :</b><br>" +
                                                                   "<b>Request coverage name  : </b>" + id1 + "<br>" +
                                                                   "<b>Expected coverage name : </b>" + id2 + "<br>" +
                                                                   "<b>Values did not match:</b><br>" +
                                                                   "<b>Request xpath          :</b><br>" + line + ".<br>" +
                                                                   "<b>Value of attributes are below:</b><br>" +
                                                                   "<b>Request attribute      :</b> " + AttributeValue[i] + " : " + ActualValue + "<br>" +
                                                                   "<b>Expected attribute     :</b> " + AttributeValue[i] + " : " + ExpectedValues;
                                        else

                                            results[resultvalue] = "<b>Id for coverage is :</b><br>" +
                                                                   "<b>Request Id : </b>" + id1 + "<br>" +
                                                                   "<b>Response Id : </b>" + id2 + "<br>" +
                                                                   "<b>Values did not match:</b><br>" +
                                                                   "<b>Request xpath:</b><br>" + line + ".<br>" +
                                                                   "<b>Value of attributes are below:</b><br>" +
                                                                   "<b>Request attribute:</b> " + AttributeValue[i] + " : " + ActualValue + "<br>" +
                                                                   "<b>Response xpath:</b> " + responsexpathfull + "<br>" +
                                                                   "<b>Response attribute:</b> " + ExpectedValue[i] + " : " + ExpectedValues;


                                        resultvalue++;

                                        String Query             = "INSERT INTO \"FAIL_TestResults\"(TestCase,\"RequestCovId\",\"ResponseCovId\",\"RequestXpath\",\"ResponseXpath\",\"RequestAttribute\",\"ReqAttributeValue\",\"ResponseAttribute\",\"ResAttributeValue\") VALUES('" + Testcasename + "','" + id1 + "','" + id2 + "','" + line + "', '" + responsexpathfull + "','" + AttributeValue[i] + "','" + ActualValue + "','" + id2 + "','" + ExpectedValues + "')";
                                        connection.executeUpdate(Query);

                                    }

                                    else {

                                        if(!project.toUpperCase().equals("FAMI"))

                                            results[resultvalue] = "<b>PASS, Name of coverage is :</b><br>" +
                                                                   "<b>Request Id                : </b>" + id1 + "<br>" +
                                                                   "<b>Response Id               : </b>" + id2 + "<br>" +
                                                                   "<b>Values matched:</b><br>" +
                                                                   "<b>Request xpath:</b><br>" + line + ".<br>" +
                                                                   "<b>Value of attributes are below:</b><br>" +
                                                                   "<b>Request attribute:</b> " + AttributeValue[i] + " : " + ActualValue + "<br>" +
                                                                   "<b>Response xpath:</b> " + "NA" + "<br>" +
                                                                   "<b>Expected attribute:</b> " + AttributeValue[i] + " : " + ExpectedValues;

                                        else
                                        results[resultvalue] = "<b>PASS, Id for coverage is :</b><br>" +
                                                               "<b>Request Id : </b>" + id1 + "<br>" +
                                                               "<b>Response Id : </b>" + id2 + "<br>" +
                                                               "<b>Values matched:</b><br>" +
                                                               "<b>Request xpath:</b><br>" + line + ".<br>" +
                                                               "<b>Value of attributes are below:</b><br>" +
                                                               "<b>Request attribute:</b> " + AttributeValue[i] + " : " + ActualValue + "<br>" +
                                                               "<b>Response xpath:</b> " + responsexpathfull + "<br>" +
                                                               "<b>Response attribute:</b> " + ExpectedValue[i] + " : " + ExpectedValues;

                                        resultvalue++;
//                                      String Query="INSERT INTO \"PASS_TestResults\"(TestCase,\"RequestCovId\",\"ResponseCovId\",\"RequestXpath\",\"ResponseXpath\",\"RequestAttribute\",\"ReqAttributeValue\",\"ResponseAttribute\",\"ResAttributeValue\") VALUES('"+tc+"','"+id1+"','"+id2+"','"+line+"', '"+responsexpathfull+"','"+arrreq[i]+"','"+name+"','"+arrres[i]+"','"+name1+"')";
//                                      connection.executeUpdate(Query);


                                    }}}}}
                }
            connection = fillo.getConnection(ExcelPath);
            }



        public String prettyFormat (String input,int indent){
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

        public String sendres () {
            return res;
        }

        public String[] send_notmatchedresults () {
            return results;
        }

        public String sendreq () {
            return requestbody;
        }


        public String Create_locator (String responselocator, String Avar) throws IOException {
            JSONObject json = new JSONObject(responselocator);
            String LT       = json.getString(Avar);
            return LT;
        }

        public void Set_Locator (String locator, String responsefile) throws IOException {

            String text         = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\WorkBook\\API\\" + responsefile)));
            String keyString    = "policyholderLocator";
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode   = mapper.readTree(text);
            ((ObjectNode) rootNode).put(keyString, locator);


            String updatedJsonString = mapper.writeValueAsString(rootNode);
            Files.writeString(Path.of(System.getProperty("user.dir") + "\\src\\main\\resources\\WorkBook\\API\\" + responsefile), updatedJsonString, StandardCharsets.UTF_8);
            System.out.println(updatedJsonString);

        }

        public void addLine(String [] lineAdd,String requestfilename) throws ParserConfigurationException, IOException, SAXException, TransformerException {

            String op1               = System.getProperty("user.dir") + "/src/main/resources/WorkBook/API/"+requestfilename;
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setIgnoringComments(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(new File(op1));

            NodeList nodes = doc.getElementsByTagName("ratingWorkSheet");

            Text a = doc.createTextNode(lineAdd[1]);
            Element p = doc.createElement(lineAdd[0]);
            p.appendChild(a);
            nodes.item(0).getParentNode().insertBefore(p, nodes.item(0));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);

            String xmlOutput = result.getWriter().toString();
            FileOutputStream output1 = new FileOutputStream(op1);
            Files.writeString(Path.of(op1), xmlOutput, StandardCharsets.UTF_8);

        }



    }

