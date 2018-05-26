package jawa.rtda.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xck
 */
public class XMethodDescriptor {
    private List<String> parameterTypes;
    private String returnType;

    public XMethodDescriptor() {
        this.parameterTypes = new ArrayList<>();
    }

    public List<String> getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(List<String> parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public void addParameterType(String t){
        parameterTypes.add(t);
    }

    static class MethodDescriptorParser {
        String raw;
        int offset;
        XMethodDescriptor parsed;

        public MethodDescriptorParser() {
            offset = 0;
        }

        public static XMethodDescriptor parserMethodDescriptor(String desc) {
            MethodDescriptorParser parser = new MethodDescriptorParser();
            return parser.parse(desc);
        }

        public XMethodDescriptor parse(String desc) {
            raw = desc.replace("'","");
            parsed = new XMethodDescriptor();
            startParams();
            parseParamTypes();
            endParams();
            parseReturnType();
            finish();
            return parsed;
        }

        public void startParams() {
            if (readUint8() != '(') {
                causePanic();
            }
        }

        public void endParams() {
            if (readUint8() != ')')
                causePanic();
        }

        public void finish() {
            if (offset != raw.length())
                causePanic();
        }

        public void causePanic() {
            throw new RuntimeException("Bad descriptor" + raw);
        }

        public int readUint8() {
            int res = raw.charAt(offset);
            offset = offset + 1;
            return res;
        }

        public void unreadUint8() {
            offset--;
        }

        public void parseParamTypes() {
//            String temp = parseFieldType();
//            if (temp.length() == 1){
//                parsed.addParameterType(temp);
//                parseParamTypes();
//            }
//            else if(temp.length() > 1){
//                for (char s : temp.toCharArray()) {
//                    if (!Character.toString(s).equals(""))
//                        parsed.addParameterType(Character.toString(s));
//                    else
//                        break;
//                }
//                parseParamTypes();
//            }
            String t;
            while(!(t = parseFieldType()).equals("")){
                parsed.addParameterType(t);
                parseParamTypes();
            }

        }

        public void parseReturnType() {
            if (readUint8() == 'V') {
                parsed.returnType = "V";
                return;
            }
            unreadUint8();
            String t = parseFieldType();
            if (!t.equals("")) {
                parsed.returnType = t;
                return;
            }
            causePanic();
        }

        public String parseFieldType() {
            switch (readUint8()) {
                case 'B':
                    return "B";
                case 'C':
                    return "C";
                case 'D':
                    return "D";
                case 'F':
                    return "F";
                case 'I':
                    return "I";
                case 'J':
                    return "J";
                case 'S':
                    return "S";
                case 'Z':
                    return "Z";
                case 'L':
                    return parseObjectType();
                case '[':
                    return parseArrayType();
                default:
                    unreadUint8();
                    return "";
            }
        }
        public String parseObjectType(){
            if(!raw.contains(";")){
                return "";
            }else{
                int objStart = offset - 1;

                int objEnd = raw.indexOf(";",offset) + 1;
                offset = objEnd;
                return raw.substring(objStart,objEnd);
            }
        }

        public String parseArrayType(){
            int arrStart = offset - 1;
            parseFieldType();
            int arrEnd = offset;
            return raw.substring(arrStart,arrEnd);
        }

        @Override
        public String toString() {
            return "MethodDescriptorParser{" +
                    "raw='" + raw + '\'' +
                    ", parsed=" + parsed +
                    '}';
        }
    }
}
