

package com.fpmislata.banco.common.json;


public interface JsonConverter {
    
    public String toJson(Object object);
    
    public Object fromJson(String json, Class clazz);
}
