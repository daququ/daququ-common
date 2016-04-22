package me.daququ.common.core.utils;

import java.util.HashMap;
import java.util.Map;

public class LocaleUtils {
	static Map<String, Boolean> frence_hash = new HashMap<String, Boolean>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			String[] arr = { "é", "è", "ê", "ë", "à", "â", "â", "å", "ä", "á",
					"ô", "ö", "ò", "û", "ù", "ú", "¡", "î", "ï", "í", "ì", "ô",
					"ö", "ò", "û", "ù", "ú", "¡", "ü", "ß", "ç", "œ" };
			for (String str : arr)
				this.put(str, true);
		}
	};

	/**
	 * 判断文本中是否包含法文
	 * @param str
	 * @return
	 */
	public static boolean isContainFrence(String str) {
		String ch = null;
		boolean r = false;
		for (int i = 0; i < str.length(); i++) {
			ch = str.substring(i, i + 1);
			if (frence_hash.get(ch) != null) {
				r = true;
			}
		}

		return r;
	}
}
