package jawa.Utils;

import java.util.Objects;

public class Sth {
    public static int getIntIndex(byte[] attrNameIndex) {
        return Integer.parseInt(Objects.requireNonNull(bytesToHexString(attrNameIndex)),10);
    }
    public static short getShortIndex(byte[] attrNameIndex) {
        return Short.parseShort(Objects.requireNonNull(bytesToHexString(attrNameIndex)),10);
    }
    public static byte getByteIndex(byte[] attrNameIndex) {
        return Byte.parseByte(Objects.requireNonNull(bytesToHexString(attrNameIndex)),10);
    }
    public static long getLongIndex(byte[] attrNameIndex) {
        return Long.parseLong(Objects.requireNonNull(bytesToHexString(attrNameIndex)),10);
    }
    public static float getFloatIndex(byte[] attrNameIndex) {
        return Float.parseFloat(Objects.requireNonNull(bytesToHexString(attrNameIndex)));
    }
    public static double getDoubleIndex(byte[] attrNameIndex) {
        return Double.parseDouble(Objects.requireNonNull(bytesToHexString(attrNameIndex)));
    }
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
