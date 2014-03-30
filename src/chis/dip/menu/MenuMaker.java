package chis.dip.menu;

import java.util.HashMap;
import java.util.Map;

import chis.dip.map.MainActivity;
import chis.dip.map.MapActivity;
import chis.dip.map.MapFeatureListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;

public class MenuMaker {

	public MenuMaker() {

	}

	public Menu makeMenuItems(Context c, Menu menu) {

		Menu initMenu = menu;
		
		Map<String,Intent> m = new HashMap<String,Intent>();
		m.put("home", new Intent(c, MainActivity.class));
		m.put("map", new Intent(c, MapActivity.class));
		m.put("feature list", new Intent(c, MapFeatureListActivity.class));

		
		int counter = 0;
		for (Map.Entry<String, Intent> map : m.entrySet()) {
			initMenu.add(map.getKey());
			initMenu.getItem(counter).setIntent(map.getValue());
			counter ++;
		}

		return initMenu;
	}



}
