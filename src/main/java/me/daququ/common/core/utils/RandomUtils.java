package me.daququ.common.core.utils;
public class RandomUtils {

  private static final String RANDOM_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  private static final java.util.Random RANDOM = new java.util.Random();

  public static String getRandomStr(int len) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < len; i++) {
      sb.append(RANDOM_STR.charAt(RANDOM.nextInt(RANDOM_STR.length())));
    }
    return sb.toString();
  }
  
  
 
}