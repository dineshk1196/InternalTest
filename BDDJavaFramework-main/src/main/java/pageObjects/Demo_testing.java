package pageObjects;
//import com.google.common.base.CommonPattern;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.X509Certificate;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.xpath.*;

import org.xml.sax.*;
import org.w3c.dom.*;


public class Demo_testing {



    public static void main(String[] args) throws IOException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, ParserConfigurationException, SAXException {
//        String endpoint="https://40.86.24.110:8024/ifoundry/api/v1/rating/rate";
//        String path="C:\\Users\\SadiqShaik\\Downloads\\Excel_auto\\TCReq_1.xml";
//        String request1=new String(Files.readAllBytes(Paths.get(path)));
//
//        DefaultHttpClient httpClient = new DefaultHttpClient();
//        SSLContext ssl_ctx = SSLContext.getInstance("TLS");
//        TrustManager[] certs = new TrustManager[] { new X509TrustManager() {
//            public X509Certificate[] getAcceptedIssuers() {
//                return null;
//            }
//
//            public void checkClientTrusted(X509Certificate[] certs, String t) {
//            }
//
//            public void checkServerTrusted(X509Certificate[] certs, String t) {
//            }
//        } };
//        ssl_ctx.init(null, certs, new SecureRandom());
//        SSLSocketFactory ssf = new SSLSocketFactory(ssl_ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//        ClientConnectionManager ccm = httpClient.getConnectionManager();
//        SchemeRegistry sr = ccm.getSchemeRegistry();
//        sr.register(new Scheme("https", 443, ssf));
//       // httpClient = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost(endpoint);
//        httpPost.setHeader("Content-type", "text/xml");
//        //httpPost.setHeader("Authorization", "Basic RmFtaXVzZXI6UEBzc3cwcmQxMjM=");
//
//
//        //httpPost.setHeader("Content-type", "application/JSON");
//        try {
//            StringEntity stringEntity = new StringEntity(request1);
//            httpPost.getRequestLine();
//            httpPost.setEntity(stringEntity);
//            //httpClient.execute(httpPost);
//
//            HttpResponse response=httpClient.execute(httpPost);
//        BufferedReader br = new BufferedReader(
//                new InputStreamReader((response.getEntity().getContent())));
//
//        if (response.getStatusLine().getStatusCode() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : "
//                    + response.getStatusLine().getStatusCode());
//        }
//
//        StringBuffer result = new StringBuffer();
//        String line = "";
//        String fr=" ";
//       while ((line = br.readLine()) != null) {
//           fr= String.valueOf(result.append(line));
//        }
//       System.out.println(fr);
//            FileOutputStream output = new FileOutputStream("C:\\Users\\SadiqShaik\\Downloads\\Excel_auto\\TCRes_1.xml");
//            String op="C:\\Users\\SadiqShaik\\Downloads\\Excel_auto\\TCRes_1.xml";
//            Files.writeString(Path.of(op),fr, StandardCharsets.UTF_8);
//        }
//        catch (Exception e)
//        {
//            throw new RuntimeException(e);
//        }
Demo_testing demo=new Demo_testing();
demo.Validationxmls();

    }

    public void Validationxmls() throws IOException, ParserConfigurationException, SAXException {
        String path = "C:\\Users\\SadiqShaik\\Downloads\\Excel_auto\\TCReq_1.xml";
        String request1 = new String(Files.readAllBytes(Paths.get(path)));

        String path1 = "C:\\Users\\SadiqShaik\\Downloads\\Excel_auto\\TCRes_1.xml";
        String request2 = new String(Files.readAllBytes(Paths.get(path1)));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new InputSource(new StringReader(request1)));

        document.getDocumentElement().normalize();
        String name = null;
        try {
            XPathFactory xpathfactory = XPathFactory.newInstance();
            XPath xpath = xpathfactory.newXPath();
            XPathExpression expr = xpath.compile("/RatingService/message/service/getRate/request/Policy/DwelFire/Location[3]");
            name = (String) expr.evaluate(document, XPathConstants.STRING);
            System.out.println(name);
        }
        catch (XPathExpressionException e)
        {
            e.printStackTrace();
        }

    }
}
