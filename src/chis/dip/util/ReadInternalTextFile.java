package chis.dip.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;

public class ReadInternalTextFile {
	
	private String logName = this.getClass().getSimpleName() + " ";

	public String readInternalFile(String fileName, AssetManager assetManager) {
		AndroidTools.print(logName + "readInternalFile");

		String text = null;
		InputStream inputStream = null;
		try {
			inputStream = assetManager.open(fileName);
			text = loadTextFile(inputStream);
			return text;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();				
				}
			}
		}
		return text;
	}

	private String loadTextFile(InputStream inputStream) throws IOException {
		AndroidTools.print(logName + "loadTextFile");

		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		byte[] bytes = new byte[4096];
		int len = 0;
		while ((len = inputStream.read(bytes)) > 0) {
			byteStream.write(bytes, 0, len);
		}
		return new String(byteStream.toByteArray(), "UTF8");
	}

}
