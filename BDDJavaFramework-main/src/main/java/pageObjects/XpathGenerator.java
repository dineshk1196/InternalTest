package pageObjects;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import utils.TestRunConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XpathGenerator {

     List<String> lines = new ArrayList<>();

     String pathApi= TestRunConfig.APIPath;

    public void generatexpath(String requestfile) throws Exception {
        String xmlFile = pathApi+"\\"+requestfile;

        File f=new File(pathApi+"\\Xpath\\"+requestfile.replace(".xml",".txt"));
        if(f.exists()){
            f.delete();
        }
        FileWriter fw=new FileWriter(f,true);
        String op=pathApi+"\\Xpath\\"+requestfile.replace(".xml",".txt");

        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(xmlFile);

        Map<String, Integer> xpathCounts = new HashMap<>();
        generateXPaths(doc.getRootElement(), "", xpathCounts,op);

        for (String str : lines) {
            fw.write(str + System.lineSeparator());
        }
        fw.close();
    }

    private  void generateXPaths(Element element, String parentXPath, Map<String, Integer> xpathCounts,String op) throws IOException {
        String currentXPath = parentXPath + "/" + element.getName();

        int count = xpathCounts.getOrDefault(currentXPath, 1);
        xpathCounts.put(currentXPath, count + 1);

        if (count > 1) {
            currentXPath += "[" + count + "]";
        }

       // System.out.println(currentXPath);
        lines.add(currentXPath);

        for (Attribute attribute : element.getAttributes()) {
            String attributeXPath = currentXPath + "/@" + attribute.getName();

            int attributeCount = xpathCounts.getOrDefault(attributeXPath, 1);
            xpathCounts.put(attributeXPath, attributeCount + 1);

            if (attributeCount > 1) {
                attributeXPath += "[" + attributeCount + "]";
            }

           // System.out.println(attributeXPath);
            lines.add(attributeXPath);

        }

        for (Element child : element.getChildren()) {
            generateXPaths(child, currentXPath, xpathCounts,op);
        }
    }
}
