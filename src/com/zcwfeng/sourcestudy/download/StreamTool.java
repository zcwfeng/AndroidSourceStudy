package com.zcwfeng.sourcestudy.download;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTool {
	public static byte[] getByte(InputStream is) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int len = 0;
		byte[] b = new byte[1024];
		while ((len = is.read(b)) != -1) {
			out.write(b, 0, len);
		}
		is.close();
		out.flush();
		return out.toByteArray();
	}
}
