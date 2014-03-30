package chis.dip.map;

import chis.dip.fragments.MyNewMapFragment;
import chis.dip.menu.MenuMaker;
import chis.dip.settings.MapApp;
import chis.dip.settings.MapApp.PREF_KEY_NAMES;
import chis.dip.util.AndroidTools;
import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.view.Menu;

public class MapActivity extends Activity  {
	
	private MyNewMapFragment mapFrag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		print("onCreate");
		
		setContentView(R.layout.map_activity);
		
		// find the retained fragment on activity restarts
        FragmentManager fm = getFragmentManager();
        mapFrag = (MyNewMapFragment) fm.findFragmentByTag("mapFrag");

        // create the fragment and data the first time
        if (mapFrag == null) {
            // add the fragment
        	mapFrag = new MyNewMapFragment();
            fm.beginTransaction().add(mapFrag, "mapFrag").commit();
        }
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		print("onCreate");
		MenuMaker mm = new MenuMaker();
    	menu = mm.makeMenuItems(getApplicationContext(),menu);
    	getMenuInflater().inflate(R.menu.main,menu);
		return true;
	}
	
	@Override
	public void onStop() {
		super.onStop();
		print("onStop");
		mapFrag.getFeatures().saveUserFeatures();
	}
	
	public void print(String message) {		
		if (((MapApp)this.getApplication()).getSettings(PREF_KEY_NAMES.PRINTLOG, "false").equals("true")) {
			String logName = this.getClass().getSimpleName() + " ";
			AndroidTools.print(logName + message);
		}
	}
	

}
