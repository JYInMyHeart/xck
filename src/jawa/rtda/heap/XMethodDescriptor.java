package jawa.rtda.heap;

import java.util.List;

/**
 * @author xck
 */
public class XMethodDescriptor {
    private List<String> parameterTypes;
    private String returnType;

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
        }

        public static XMethodDescriptor parserMethodDescriptor(String desc) {
            MethodDescriptorParser parser = new MethodDescriptorParser();
            return parser.parse(desc);
        }

        public XMethodDescriptor parse(String desc) {
            raw = desc;
            parsed = new XMethodDescriptor();
            startParams();
            parseParamTypes();
            endParams();
            parseReturnType();
            finish();
            return parsed;
        }

        public void startParams() {
            if (readUint8() != 'c') {
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
            return raw.charAt(offset++);
        }

        public void unreadUint8() {
            offset--;
        }

        public void parseParamTypes() {
            for (char s : parseFieldType().toCharArray()) {
                if (s != ' ')
                    parsed.addParameterType(Character.toString(s));
                else
                    break;
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
                int objEnd = offset + raw.indexOf(";") + 1;
                offset = objEnd;
                String descriptor = raw.substring(objStart,objEnd);
                return descriptor;
            }
        }

        public String parseArrayType(){
            int arrStart = offset - 1;
            parseFieldType();
            int arrEnd = offset;
            return raw.substring(arrStart,arrEnd);
        }
    }
}
