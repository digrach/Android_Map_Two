package chis.dip.map;

import chis.dip.fragments.MyListFragment;
import chis.dip.menu.MenuMaker;
import chis.dip.settings.MapApp;
import chis.dip.settings.MapApp.PREF_KEY_NAMES;
import chis.dip.util.AndroidTools;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MapFeatureListActivity extends Activity {
		
	private MyListFragment listMapFrag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		print("onCreate");

		setContentView(R.layout.activity_map_feature_list);

		// find the retained fragment on activity restarts
        FragmentManager fm = getFragmentManager();
        listMapFrag = (MyListFragment) fm.findFragmentByTag("listMapFrag");

        // create the fragment and data the first time
        if (listMapFrag == null) {
            // add the fragment
        	listMapFrag = new MyListFragment();
            fm.beginTransaction().add(listMapFrag, "listMapFrag").commit();
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		print("onCreateOptionsMenu");

		MenuMaker mm = new MenuMaker();
    	menu = mm.makeMenuItems(getApplicationContext(),menu);
    	getMenuInflater().inflate(R.menu.main,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		print("onOptionsItemSelected");

		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void print(String message) {		
		if (((MapApp)this.getApplication()).getSettings(PREF_KEY_NAMES.PRINTLOG, "false").equals("true")) {
			String logName = this.getClass().getSimpleName() + " ";
			AndroidTools.print(logName + message);
		}
	}

//	/**
//	 * A placeholder fragment containing a simple view.
//	 */
//	public static class PlaceholderFragment extends Fragment {
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(
//					R.layout.fragment_map_feature_list, container, false);
//			return rootView;
//		}
//	}

}
