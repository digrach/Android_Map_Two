package chis.dip.util;

import java.io.IOException;

import chis.dip.settings.MapApp;
import android.content.Context;
import android.util.Log;

public class AndroidTools {
	
	public static boolean assetExists(Context c,String filePath) {
		boolean exists = false;
		try {
			if(c.getAssets().open(filePath) != null) {
				exists = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return exists;
	}
	
	public static void print(String message) {
		Log.d("!*!*!*!*!*!*!", message);
	}

}
