package chis.dip.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import chis.dip.settings.MapApp;
import chis.dip.settings.MapApp.PREF_KEY_NAMES;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class WriteExternalTextFile {
	
	private Context context;
	
	public boolean writeExternalFile(String fileName, String text, Context c) {
		context = c;
		
		print("writeExternalFile");

		if (!checkExternalStorageExists(c)) {
			return false;
		}
		try {
			File f = makeFile(fileName);
			writeTextToFile(f, text);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;	
	}
	
	private boolean checkExternalStorageExists(Context c) {
		print("checkExternalStorageExists");

		String state = Environment.getExternalStorageState();
		if (!state.equals(Environment.MEDIA_MOUNTED)) {
			return false;
		}
		return true;
	}
	
	public File makeFile(String fileName) {
		print("makeFile");

		File externalDir = Environment.getExternalStorageDirectory();
		File textFile = new File(externalDir.getAbsolutePath()
				+ File.separator + fileName);
		return textFile;
	}
	
	private void writeTextToFile(File file, String text) throws IOException {
		print("writeTextToFile");

		Log.d("writeTextToFile: ", file.getAbsolutePath());
		Log.d("writeTextToFile: ", text);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(text);
		writer.close();
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
