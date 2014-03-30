package chis.dip.map;

import chis.dip.menu.MenuMaker;
import chis.dip.settings.MapApp;
import chis.dip.settings.MapApp.PREF_KEY_NAMES;
import chis.dip.util.AndroidTools;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		print("onCreate");
		
		setContentView(R.layout.activity_main);
		
		// Turn printing on.
		((MapApp)this.getApplication()).setSettings(chis.dip.settings.MapApp.PREF_KEY_NAMES.PRINTLOG, true);
		
		// Go straight to map activity.
		Intent intent = new Intent(this, MapActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		print("onCreateOptionsMenu");
		MenuMaker mm = new MenuMaker();
    	menu = mm.makeMenuItems(getApplicationContext(),menu);
    	getMenuInflater().inflate(R.menu.main,menu);
		return true;
	}
	
	public void print(String message) {		
		if (((MapApp)this.getApplication()).getSettings(PREF_KEY_NAMES.PRINTLOG, "false").equals("true")) {
			String logName = this.getClass().getSimpleName() + " ";
			AndroidTools.print(logName + message);
		}
	}

}
