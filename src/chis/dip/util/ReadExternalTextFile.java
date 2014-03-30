package chis.dip.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import chis.dip.settings.MapApp;
import chis.dip.settings.MapApp.PREF_KEY_NAMES;
import android.content.Context;
import android.os.Environment;

public class ReadExternalTextFile {

	private Context context;

	public String readExternalFile(String fileName, Context c) {
		context = c;
		print("readExternalFile");

		String fileContents = null;
		if (!checkExternalStorageExists(c)) {
			return fileContents;
		}
		try {
			File f = makeFile(fileName);
			fileContents = read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContents;
	}

	private boolean checkExternalStorageExists(Context c) {
		print("checkExternalStorageExists");

		String state = Environment.getExternalStorageState();
		if (!state.equals(Environment.MEDIA_MOUNTED)) {
			return false;
		}
		return true;
	}

	private File makeFile(String fileName) {
		print("makeFile");

		File externalDir = Environment.getExternalStorageDirectory();
		File textFile = new File(externalDir.getAbsolutePath()
				+ File.separator + fileName);
		return textFile;
	}

	private String read(File file) throws IOException {
		print("read");

		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuilder text = new StringBuilder();
		String line;
		while((line = reader.readLine()) != null) {
			text.append(line);
		}
		reader.close();
		return text.toString();
	}

	public void print(String message) {		
//		if (((MapApp)context.getSharedPreferences("myprefs", 0)).getSettings(PREF_KEY_NAMES.PRINTLOG, "false").equals("true")) {
//			String logName = this.getClass().getSimpleName() + " ";
//			AndroidTools.print(logName + message);
//		}
		String logName = this.getClass().getSimpleName() + " ";
		AndroidTools.print(logName + message);
	}

}
