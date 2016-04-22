package me.daququ.common.core.utils;
public class BitsUtils
{
  public static boolean getBoolean(byte[] b, int off)
  {
    return b[off] != 0;
  }

  public static char getChar(byte[] b, int off) {
    return (char)(((b[(off + 1)] & 0xFF) << 0) + ((b[(off + 0)] & 0xFF) << 8));
  }

  public static short getShort(byte[] b, int off)
  {
    return (short)(((b[(off + 1)] & 0xFF) << 0) + ((b[(off + 0)] & 0xFF) << 8));
  }

  public static int getInt(byte[] b, int off)
  {
    return ((b[(off + 3)] & 0xFF) << 0) + ((b[(off + 2)] & 0xFF) << 8) + ((b[(off + 1)] & 0xFF) << 16) + ((b[(off + 0)] & 0xFF) << 24);
  }

  public static float getFloat(byte[] b, int off)
  {
    int i = ((b[(off + 3)] & 0xFF) << 0) + ((b[(off + 2)] & 0xFF) << 8) + ((b[(off + 1)] & 0xFF) << 16) + ((b[(off + 0)] & 0xFF) << 24);

    return Float.intBitsToFloat(i);
  }

  public static long getLong(byte[] b, int off) {
    return ((b[(off + 7)] & 0xFF) << 0) + ((b[(off + 6)] & 0xFF) << 8) + ((b[(off + 5)] & 0xFF) << 16) + ((b[(off + 4)] & 0xFF) << 24) + ((b[(off + 3)] & 0xFF) << 32) + ((b[(off + 2)] & 0xFF) << 40) + ((b[(off + 1)] & 0xFF) << 48) + ((b[(off + 0)] & 0xFF) << 56);
  }

  public static double getDouble(byte[] b, int off)
  {
    long j = ((b[(off + 7)] & 0xFF) << 0) + ((b[(off + 6)] & 0xFF) << 8) + ((b[(off + 5)] & 0xFF) << 16) + ((b[(off + 4)] & 0xFF) << 24) + ((b[(off + 3)] & 0xFF) << 32) + ((b[(off + 2)] & 0xFF) << 40) + ((b[(off + 1)] & 0xFF) << 48) + ((b[(off + 0)] & 0xFF) << 56);

    return Double.longBitsToDouble(j);
  }

  public static void putBoolean(byte[] b, int off, boolean val)
  {
    b[off] = (byte)(val ? 1 : 0);
  }

  public static void putChar(byte[] b, int off, char val) {
    b[(off + 1)] = (byte)(val >>> '\000');
    b[(off + 0)] = (byte)(val >>> '\b');
  }

  public static void putShort(byte[] b, int off, short val) {
    b[(off + 1)] = (byte)(val >>> 0);
    b[(off + 0)] = (byte)(val >>> 8);
  }

  public static void putInt(byte[] b, int off, int val) {
    b[(off + 3)] = (byte)(val >>> 0);
    b[(off + 2)] = (byte)(val >>> 8);
    b[(off + 1)] = (byte)(val >>> 16);
    b[(off + 0)] = (byte)(val >>> 24);
  }

  public static void putFloat(byte[] b, int off, float val) {
    int i = Float.floatToIntBits(val);
    b[(off + 3)] = (byte)(i >>> 0);
    b[(off + 2)] = (byte)(i >>> 8);
    b[(off + 1)] = (byte)(i >>> 16);
    b[(off + 0)] = (byte)(i >>> 24);
  }

  public static void putLong(byte[] b, int off, long val) {
    b[(off + 7)] = (byte)(int)(val >>> 0);
    b[(off + 6)] = (byte)(int)(val >>> 8);
    b[(off + 5)] = (byte)(int)(val >>> 16);
    b[(off + 4)] = (byte)(int)(val >>> 24);
    b[(off + 3)] = (byte)(int)(val >>> 32);
    b[(off + 2)] = (byte)(int)(val >>> 40);
    b[(off + 1)] = (byte)(int)(val >>> 48);
    b[(off + 0)] = (byte)(int)(val >>> 56);
  }

  public static void putDouble(byte[] b, int off, double val) {
    long j = Double.doubleToLongBits(val);
    b[(off + 7)] = (byte)(int)(j >>> 0);
    b[(off + 6)] = (byte)(int)(j >>> 8);
    b[(off + 5)] = (byte)(int)(j >>> 16);
    b[(off + 4)] = (byte)(int)(j >>> 24);
    b[(off + 3)] = (byte)(int)(j >>> 32);
    b[(off + 2)] = (byte)(int)(j >>> 40);
    b[(off + 1)] = (byte)(int)(j >>> 48);
    b[(off + 0)] = (byte)(int)(j >>> 56);
  }
}