package  datatableConvertor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DefaultDataTableEntryTransformer;

import java.lang.reflect.Type;
import java.util.Map;

public class TypeRegistryConfiguration {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @DefaultDataTableEntryTransformer
    public Object defaultDataTableEntry(Map<String, String> entry, Type toValueType) {
        return objectMapper.convertValue(entry, objectMapper.constructType(toValueType));
    }

}