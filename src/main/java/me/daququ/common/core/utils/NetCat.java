package me.daququ.common.core.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class NetCat {
	public static byte[] nc(String host, int port, String message) throws Exception {
		Socket socket = new Socket(host, port);
		socket.setTcpNoDelay(true);
		socket.setKeepAlive(false);
		socket.setReuseAddress(true);
		socket.setSoTimeout(30000);
		OutputStream output1 = socket.getOutputStream();
		InputStream input2 = socket.getInputStream();
		output1.write(message.getBytes());

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		PrintWriter writer = new PrintWriter(bos);
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(input2));
		String line;
		while ((line = reader.readLine()) != null) {
			writer.println(line);
			writer.flush();
		}
		socket.shutdownOutput();
		socket.close();
		return bos.toByteArray();
	}
}
