

package com.fpmislata.banco.common.json.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.banco.common.json.JsonConverter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JsonConverterImplJackson implements JsonConverter{

    @Override
    public String toJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        
        try {
            String json = objectMapper.writeValueAsString(object);
            return json;
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Object fromJson(String json, Class clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
