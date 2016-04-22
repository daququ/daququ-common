package me.daququ.common.core.utils;

import java.io.File;


public class OSUtils  {

	public static final String OS_NAME = System.getProperty("os.name");
	  public static final String OS_VERSION = System.getProperty("os.version").toLowerCase();
	  public static final String OS_ARCH = System.getProperty("os.arch");
	  public static final String JAVA_VERSION = System.getProperty("java.version");
	  public static final String JAVA_RUNTIME_VERSION = System.getProperty("java.runtime.version");
	  public static final String ARCH_DATA_MODEL = System.getProperty("sun.arch.data.model");
	  public static final String SUN_DESKTOP = System.getProperty("sun.desktop", "");

	  private static final String _OS_NAME = OS_NAME.toLowerCase();
	  public static final boolean isWindows = _OS_NAME.startsWith("windows");
	  public static final boolean isWindowsNT = _OS_NAME.startsWith("windows nt");
	  public static final boolean isWindows2000 = _OS_NAME.startsWith("windows 2000");
	  public static final boolean isWindows2003 = _OS_NAME.startsWith("windows 2003");
	  public static final boolean isWindowsXP = _OS_NAME.startsWith("windows xp");
	  public static final boolean isWindowsVista = _OS_NAME.startsWith("windows vista");
	  public static final boolean isWindows7 = _OS_NAME.startsWith("windows 7");
	  public static final boolean isWindows9x = (_OS_NAME.startsWith("windows 9")) || (_OS_NAME.startsWith("windows me"));
	  public static final boolean isOS2 = (_OS_NAME.startsWith("os/2")) || (_OS_NAME.startsWith("os2"));
	  public static final boolean isMac = _OS_NAME.startsWith("mac");
	  public static final boolean isFreeBSD = _OS_NAME.startsWith("freebsd");
	  public static final boolean isLinux = _OS_NAME.startsWith("linux");
	  public static final boolean isSolaris = _OS_NAME.startsWith("sunos");
	  public static final boolean isUnix = (!isWindows) && (!isOS2);

	  private static final String _SUN_DESKTOP = SUN_DESKTOP.toLowerCase();
	  public static final boolean isKDE = _SUN_DESKTOP.contains("kde");
	  public static final boolean isGnome = _SUN_DESKTOP.contains("gnome");

	  public static final boolean hasXdgOpen = (isUnix) && (new File("/usr/bin/xdg-open").canExecute());

	  public static final boolean isMacSystemMenu = (isMac) && ("true".equals(System.getProperty("apple.laf.useScreenMenuBar")));

	  public static final boolean isFileSystemCaseSensitive = (!isWindows) && (!isOS2) && (!isMac);
	  public static final boolean areSymLinksSupported = (isUnix) || ((isWindows) && (OS_VERSION.compareTo("6.0") >= 0) );

	  public static final boolean is32Bit = (ARCH_DATA_MODEL == null) || (ARCH_DATA_MODEL.equals("32"));
	  public static final boolean is64Bit = !is32Bit;
	  public static final boolean isAMD64 = "amd64".equals(OS_ARCH);
	  public static final boolean isMacIntel64 = (isMac) && ("x86_64".equals(OS_ARCH));

	  public static final String nativeFileManagerName = isWindows ? "Explorer" : isKDE ? "Konqueror" : isGnome ? "Nautilus" : isMac ? "Finder" : "File Manager";

	  public static final boolean isMacOSTiger = isTiger();

	  public static final boolean isIntelMac = isIntelMac();

	  public static final boolean isMacOSLeopard = isLeopard();

	  public static final boolean isMacOSSnowLeopard = isSnowLeopard();

	  public static final boolean isMacOSLion = isLion();

	  public static final boolean isMacOSMountainLion = isMountainLion();

	  public static boolean X11PasteEnabledSystem = (isUnix) && (!isMac);

	  private static boolean isIntelMac() {
	    return (isMac) && ("i386".equals(OS_ARCH));
	  }

	  private static boolean isTiger() {
	    return (isMac) && (!OS_VERSION.startsWith("10.0")) && (!OS_VERSION.startsWith("10.1")) && (!OS_VERSION.startsWith("10.2")) && (!OS_VERSION.startsWith("10.3"));
	  }

	  private static boolean isLeopard()
	  {
	    return (isMac) && (isTiger()) && (!OS_VERSION.startsWith("10.4"));
	  }

	  private static boolean isSnowLeopard() {
	    return (isMac) && (isLeopard()) && (!OS_VERSION.startsWith("10.5"));
	  }

	  private static boolean isLion() {
	    return (isMac) && (isSnowLeopard()) && (!OS_VERSION.startsWith("10.6"));
	  }

	  private static boolean isMountainLion() {
	    return (isMac) && (isLion()) && (!OS_VERSION.startsWith("10.7"));
	  }

	  
	  public static String getMacOSMajorVersion()
	  {
	    String tmp6_3 = getMacOSMajorVersion(OS_VERSION); if (tmp6_3 == null) throw new IllegalStateException("method com/intellij/openapi/util/SystemInfo.getMacOSMajorVersion must not return null"); return tmp6_3;
	  }

	  public static String getMacOSMajorVersion(String version) {
	    int[] parts = getMacOSVersionParts(version);
	    return String.format("%d.%d", new Object[] { Integer.valueOf(parts[0]), Integer.valueOf(parts[1]) });
	  }

	  
	  public static String getMacOSVersionCode()
	  {
	    String tmp6_3 = getMacOSVersionCode(OS_VERSION); if (tmp6_3 == null) throw new IllegalStateException("method com/intellij/openapi/util/SystemInfo.getMacOSVersionCode must not return null"); return tmp6_3;
	  }

	  
	  public static String getMacOSMajorVersionCode()
	  {
	    String tmp6_3 = getMacOSMajorVersionCode(OS_VERSION); if (tmp6_3 == null) throw new IllegalStateException("method com/intellij/openapi/util/SystemInfo.getMacOSMajorVersionCode must not return null"); return tmp6_3;
	  }

	  
	  public static String getMacOSMinorVersionCode()
	  {
	    String tmp6_3 = getMacOSMinorVersionCode(OS_VERSION); if (tmp6_3 == null) throw new IllegalStateException("method com/intellij/openapi/util/SystemInfo.getMacOSMinorVersionCode must not return null"); return tmp6_3;
	  }
	  
	  public static String getMacOSVersionCode(String version) {
	    if (version == null) throw new IllegalArgumentException("Argument 0 for parameter of com/intellij/openapi/util/SystemInfo.getMacOSVersionCode must not be null"); int[] parts = getMacOSVersionParts(version);
	    String tmp61_58 = String.format("%02d%d%d", new Object[] { Integer.valueOf(parts[0]), Integer.valueOf(normalize(parts[1])), Integer.valueOf(normalize(parts[2])) }); if (tmp61_58 == null) throw new IllegalStateException("method com/intellij/openapi/util/SystemInfo.getMacOSVersionCode must not return null"); return tmp61_58;
	  }
	  
	  public static String getMacOSMajorVersionCode(String version) {
	    if (version == null) throw new IllegalArgumentException("Argument 0 for parameter of com/intellij/openapi/util/SystemInfo.getMacOSMajorVersionCode must not be null"); int[] parts = getMacOSVersionParts(version);
	    String tmp56_53 = String.format("%02d%d%d", new Object[] { Integer.valueOf(parts[0]), Integer.valueOf(normalize(parts[1])), Integer.valueOf(0) }); if (tmp56_53 == null) throw new IllegalStateException("method com/intellij/openapi/util/SystemInfo.getMacOSMajorVersionCode must not return null"); return tmp56_53;
	  }
	  
	  public static String getMacOSMinorVersionCode(String version) {
	    if (version == null) throw new IllegalArgumentException("Argument 0 for parameter of com/intellij/openapi/util/SystemInfo.getMacOSMinorVersionCode must not be null"); int[] parts = getMacOSVersionParts(version);
	    String tmp46_43 = String.format("%02d%02d", new Object[] { Integer.valueOf(parts[1]), Integer.valueOf(parts[2]) }); if (tmp46_43 == null) throw new IllegalStateException("method com/intellij/openapi/util/SystemInfo.getMacOSMinorVersionCode must not return null"); return tmp46_43;
	  }

	  private static int[] getMacOSVersionParts(String version) {
	    if (version == null) throw new IllegalArgumentException("Argument 0 for parameter of com/intellij/openapi/util/SystemInfo.getMacOSVersionParts must not be null"); String[] parts = version.split(".");
	    while (parts.length< 3) {
	      parts[2] = "0";
	    }
	    return new int[] { toInt((String)parts[0]), toInt((String)parts[1]), toInt((String)parts[2]) };
	  }

	  private static int normalize(int number) {
	    return number > 9 ? 9 : number;
	  }

	  private static int toInt(String string) {
	    try {
	      return Integer.valueOf(string).intValue();
	    } catch (NumberFormatException e) {
	    }
	    return 0;
	  }

	 
	  public static int getIntProperty(String key, int defaultValue) {
	    if (key == null) throw new IllegalArgumentException("Argument 0 for parameter of com/intellij/openapi/util/SystemInfo.getIntProperty must not be null"); String value = System.getProperty(key);
	    if (value != null) {
	      try {
	        return Integer.parseInt(value);
	      }
	      catch (NumberFormatException ignored)
	      {
	      }
	    }
	    return defaultValue;
	  }
	  
	  private static String ourTestUserName;

 

	  public static String getUserName() {
	    return ourTestUserName != null ? ourTestUserName : System.getProperty("user.name");
	  }

	  public static void setTestUserName(String name) {
	    ourTestUserName = name;
	  }

	  public static String getLineSeparator()
	  {
	    return System.getProperty("line.separator");
	  }

	  public static String getOsName()
	  {
	    return System.getProperty("os.name");
	  }

	  

	  public static String getJavaVmVendor()
	  {
	    return System.getProperty("java.vm.vendor");
	  }
 
}
