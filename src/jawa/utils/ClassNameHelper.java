package jawa.utils;

import java.util.HashMap;
import java.util.Map;

public class ClassNameHelper{
    private final static Map<String,String> primitiveTypes = new HashMap<>();
    static{
        primitiveTypes.put("void","V");
        primitiveTypes.put("boolean","Z");
        primitiveTypes.put("byte","B");
        primitiveTypes.put("short","S");
        primitiveTypes.put("int","I");
        primitiveTypes.put("long","J");
        primitiveTypes.put("char","C");
        primitiveTypes.put("float","F");
        primitiveTypes.put("double","D");
        }

    public static String getArrayClassName(String className){
        return "[" + toDescriptor(className);
    }
    public static String toDescriptor(String className){
        if(className.charAt(0) == '[')
            return className;
        if(primitiveTypes.containsKey(className))
            return primitiveTypes.get(className);
        return "L" + className + ";";
    }
}
