package com.qzw.filemask.util;

import java.nio.ByteBuffer;

/**
 * 字节处理
 * @author quanzongwei
 * @date 2020/1/12
 */
public class ByteUtil {

    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    /**
     * 将byte转化为16进制
     */
    public static String byteToHex(byte[] bs) {
        if (0 == bs.length) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < bs.length; i++) {
                String s = Integer.toHexString(bs[i] & 0xFF);
                if (1 == s.length()) {
                    sb.append("0");
                }
                sb = sb.append(s.toUpperCase());
            }
            return sb.toString();
        }
    }

    /**
     * 将16进制转化为byte
     */
    public static byte[] hexToByte(String ciphertext) {
        byte[] cipherBytes = ciphertext.getBytes();
        if ((cipherBytes.length % 2) != 0) {
            throw new IllegalArgumentException("长度不为偶数");
        } else {
            byte[] result = new byte[cipherBytes.length / 2];
            for (int i = 0; i < cipherBytes.length; i += 2) {
                String item = new String(cipherBytes, i, 2);
                result[i / 2] = (byte) Integer.parseInt(item, 16);
            }
            return result;
        }
    }

    public static byte[] shortToByte(short s) {
        byte[] b = new byte[2];
        b[1] = (byte) (s >> 8);
        b[0] = (byte) (s >> 0);
        return b;
    }

    public static short byteToShort(byte[] b) {
        return (short) (((b[1] << 8) | b[0] & 0xff));
    }


    public static int getUnsignedByte(byte data) {      //将data字节型数据转换为0~255 (0xFF 即BYTE)。
        return data & 0x0FF;
    }

    public static int getUnsignedByte(short data) {      //将data字节型数据转换为0~65535 (0xFFFF 即 WORD)。
        return data & 0x0FFFF;
    }

    public static long getUnsignedIntt(int data) {     //将int数据转换为0~4294967295 (0xFFFFFFFF即DWORD)。
        return data & 0x0FFFFFFFF;
    }
}