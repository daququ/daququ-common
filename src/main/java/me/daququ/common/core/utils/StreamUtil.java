package me.daququ.common.core.utils;

import java.io.*;

public class StreamUtil
{
  public static int copyStreamContent(InputStream inputStream, OutputStream outputStream)
    throws IOException
  {
    byte[] buffer = new byte[10240];

    int total = 0;
    int count;
    while ((count = inputStream.read(buffer)) > 0) {
      outputStream.write(buffer, 0, count);
      total += count;
    }
    return total;
  }

  public static byte[] loadFromStream(InputStream inputStream) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      copyStreamContent(inputStream, outputStream);
    }
    finally {
      inputStream.close();
    }
    return outputStream.toByteArray();
  }

  public static String readText(InputStream inputStream) throws IOException {
    byte[] data = loadFromStream(inputStream);
    return new String(data);
  }

  public static String readText(InputStream inputStream, String encoding) throws IOException {
    byte[] data = loadFromStream(inputStream);
    return new String(data, encoding);
  }

  public static String convertSeparators(String s) {
    try {
      return new String(readTextAndConvertSeparators(new StringReader(s)));
    } catch (IOException e) {throw new RuntimeException(e);
    }
    
  }

  public static char[] readTextAndConvertSeparators(Reader reader) throws IOException
  {
    char[] buffer = readText(reader);

    int dst = 0;
    char prev = ' ';
    for (char c : buffer) {
      switch (c) {
      case '\r':
        buffer[(dst++)] = '\n';
        break;
      case '\n':
        if (prev == '\r') break;
        buffer[(dst++)] = '\n'; break;
      default:
        buffer[(dst++)] = c;
      }

      prev = c;
    }

    char[] chars = new char[dst];
    System.arraycopy(buffer, 0, chars, 0, chars.length);
    return chars;
  }

  public static String readTextFrom(Reader reader) throws IOException {
    return new String(readText(reader));
  }

  private static char[] readText(Reader reader) throws IOException {
    CharArrayWriter writer = new CharArrayWriter();

    char[] buffer = new char[2048];
    while (true) {
      int read = reader.read(buffer);
      if (read < 0) break;
      writer.write(buffer, 0, read);
    }

    return writer.toCharArray();
  }
}