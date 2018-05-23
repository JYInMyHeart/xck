package jawa.Utils;

import java.util.Objects;

public class Sth {
    public static int getIntIndex(byte[] attrNameIndex) {
        return Integer.parseInt(Objects.requireNonNull(bytesToIntHexString(attrNameIndex)), 16);
    }

    public static short getShortIndex(byte[] attrNameIndex) {
        return Short.parseShort(Objects.requireNonNull(bytesToIntHexString(attrNameIndex)), 16);
    }

    public static byte getByteIndex(byte[] attrNameIndex) {
        return Byte.parseByte(Objects.requireNonNull(bytesToIntHexString(attrNameIndex)), 16);
    }

    public static long getLongIndex(byte[] attrNameIndex) {
        return bytesToLong(attrNameIndex);
    }

    public static float getFloatIndex(byte[] attrNameIndex) {
        return Float.intBitsToFloat(getIntIndex(attrNameIndex));
    }

    public static double getDoubleIndex(byte[] attrNameIndex) {
        return Double.longBitsToDouble(getLongIndex(attrNameIndex));
    }

    public static String bytesToIntHexString(byte[] src) {
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

    //todo fix this method (this is a wrong version
    public static long bytesToLong(byte[] src) {
        long res = 0xffffffff;
        for (int i = 0; i < src.length; i++) {
            res &= src[i] << (src.length - 1 - i);
        }
        return res;
    }


}
