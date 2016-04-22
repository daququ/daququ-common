// =================================================================================================
// Copyright 2011 Twitter, Inc.
// -------------------------------------------------------------------------------------------------
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this work except in compliance with the License.
// You may obtain a copy of the License in the LICENSE file, or at:
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// =================================================================================================

package me.daququ.common.core.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Utilities for working with Files
 *
 * @author Florian Leibert
 */
public final class FileUtils extends org.apache.commons.io.FileUtils {

	private FileUtils() {
	}

	/**
	 * recursively deletes the path and all it's content and returns true if it
	 * succeeds Note that the content could be partially deleted and the method
	 * return false
	 *
	 * @param path
	 *            the path to delete
	 * @return true if the path was deleted
	 */
	public static boolean forceDeletePath(File path) {
		if (path == null) {
			return false;
		}
		if (path.exists() && path.isDirectory()) {
			File[] files = path.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					forceDeletePath(file);
				} else {
					file.delete();
				}
			}
		}
		return path.delete();
	}

	public static boolean byte2File(byte[] buff, String filePath,String fileName) throws Exception {
		boolean retFlag = true;
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!(dir.exists() && dir.isDirectory())) {
				dir.mkdirs(); 
			}
			file = new File(filePath + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(buff);
		} catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					throw new Exception(e.getMessage(),e);
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					throw new Exception(e.getMessage(),e);
				}
			}
		}
		return retFlag;
	}
}
