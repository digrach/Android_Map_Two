package chis.dip.model;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import chis.dip.settings.MapApp;
import chis.dip.settings.MapApp.PREF_KEY_NAMES;
import chis.dip.util.AndroidTools;
import chis.dip.util.ReadExternalTextFile;
import chis.dip.util.ReadInternalTextFile;
import chis.dip.util.WriteExternalTextFile;

public class MapFeatureManager {
	
	private List<MapFeature> featureList;
	private List<MapFeature> userFeatureList;
	private AssetManager assetManager;
	private Context context;
	private final String FEATURES_FILE_NAME = "texts/mapfeatures.txt";
	private final String USER_FEATURES_FILE_NAME = "userfeaturedata.txt";

	public MapFeatureManager(Context c, AssetManager assetManager) {
		print("new");
		featureList = new ArrayList<MapFeature>();
		userFeatureList = new ArrayList<MapFeature>();
		this.assetManager = assetManager;
		context = c;
		initialiseFeatureData(this.assetManager);
		Log.d("new map manager","new");
	}

	private void initialiseFeatureData(AssetManager assetManager) {
		print("initialiseFeatureData");
		ReadInternalTextFile ri = new ReadInternalTextFile();
		String internalText = ri.readInternalFile(FEATURES_FILE_NAME, assetManager);
		
		print("initialiseFeatureData" + "loading internal file" + internalText);
		loadObjects(internalText, featureList);
		
		//		WriteExternalTextFile w = new WriteExternalTextFile();
		//		w.makeFile(USER_FEATURES_FILE_NAME);

		ReadExternalTextFile re = new ReadExternalTextFile();
		String externalText = re.readExternalFile(USER_FEATURES_FILE_NAME, context);

		print("initialiseFeatureData " + "Read back external string " + " e " + Integer.toString(externalText.length()));
		loadObjects(externalText,userFeatureList);
	}

	private void loadObjects(String dataString, List<MapFeature> list) {
		print("loadObjects");

		if (dataString != null && dataString.length() > 0) {
			String[] feature = dataString.split(";");
			print("feature " + Integer.toString(feature.length));

			for (String f : feature) {
				String[] currentFeature = f.split(",");
				print("currentFeature " + Integer.toString(currentFeature.length));

				MapFeature mf = new MapFeature(currentFeature[0], 
						new LatLng(Double.parseDouble(currentFeature[1]),Double.parseDouble(currentFeature[2])), 
						currentFeature[4], 
						new MapFeatureType(currentFeature[5]));
				mf.setPostcode(currentFeature[3]);
				addFeature(mf, list);
			}

		}


	}

	public boolean addFeature(MapFeature feature) {
		print("addFeature");
		return addFeature(feature, featureList);
	}

	private boolean addFeature(MapFeature feature, List<MapFeature> list) {
		print("addFeature");
		if (list.contains(feature)) return false;
		list.add(feature);
		return true;
	}

	public boolean addUserFeature(MapFeature feature) {
		print("addUserFeature");
		if (userFeatureList.contains(feature)) return false;
		userFeatureList.add(feature);
		return true;
	}

	public void saveUserFeatures() {
		print("saveUserFeatures");
		WriteExternalTextFile w = new WriteExternalTextFile();
		StringBuilder s = new StringBuilder();
		for (MapFeature m : userFeatureList) {
			s.append(m.toSerialiseString());
			s.append(";");
		}
		w.writeExternalFile(USER_FEATURES_FILE_NAME, s.toString(), context);
	}

	public boolean removeFeature(String featureName) {
		print("removeFeature");
		for(MapFeature f : featureList) {
			if (f.getName().equals(featureName)) {
				featureList.remove(f);
				return true;
			}
		}
		return false;
	}

	public List<MapFeature> getMapFeatures() {
		print("getMapFeatures");
		return featureList;
	}

	public List<MapFeature> getUserFeatureList() {
		print("getUserFeatureList");
		return userFeatureList;
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
