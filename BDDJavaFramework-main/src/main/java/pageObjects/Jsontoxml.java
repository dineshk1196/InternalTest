package pageObjects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

public class Jsontoxml {

    public String ConvJsontoXml(String json) {
                String xml = convertJsonToXml(json);
                return xml;
    }

    private  String convertJsonToXml(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);

            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);

            return xmlMapper.writeValueAsString(convertJsonNode(jsonNode));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JsonNode convertJsonNode(JsonNode node) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        ArrayNode arrayNode = objectMapper.createArrayNode();

        if (node.isObject()) {
            node.fields().forEachRemaining(entry -> {
                String key = entry.getKey();
                JsonNode value = entry.getValue();

                if (key.matches("^\\d.*")) {
                    key = "a" + key;
                }

                objectNode.set(key, convertJsonNode(value));
            });
            return objectNode;
        } else if (node.isArray()) {
            node.elements().forEachRemaining(element -> arrayNode.add(convertJsonNode(element)));
            return arrayNode;
        } else {
            return node;
        }
    }
}
