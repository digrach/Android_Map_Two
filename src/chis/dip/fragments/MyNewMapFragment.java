package chis.dip.fragments;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import chis.dip.model.MapFeature;
import chis.dip.model.MapFeatureManager;
import chis.dip.model.MapFeatureType;
import chis.dip.settings.MapApp;
import chis.dip.settings.MapApp.PREF_KEY_NAMES;
import chis.dip.util.AndroidTools;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyNewMapFragment extends MapFragment implements GooglePlayServicesClient.ConnectionCallbacks, 
GooglePlayServicesClient.OnConnectionFailedListener, GoogleMap.OnMapLongClickListener {

	private GoogleMap mMap;
	private MapFeatureManager features;
	private LocationClient mLocationClient;
	private final String MARKER_ICON_DIRECTORY = "markers/";
	private Location mCurrentLocation;
	private LatLng myLocation;


	public MyNewMapFragment() {
		// Must remain no args and empty.
	}

	// this method is only called once for this fragment
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		print("onCreate");
		// retain this fragment
		setRetainInstance(true);
	}

	@Override
	public void onStart() {
		super.onStart();
		print("onStart");
		// Get and initialize the map 
		// once it has been added to the activity.
		mMap = getMap();
		initialiseMap();
	}

	public void initialiseMap() {
		//newInstance(makeGoogleMapOptions());
		print("initialiseMap");
		mMap.setMyLocationEnabled(true);
		mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		mMap.setOnMapLongClickListener(this);
		features = new MapFeatureManager(getActivity(),getActivity().getAssets());
		addFeaturesToMap();
		setStartCamera();
	}

	private void setStartCamera() {
		print("setStartCamera");
		mLocationClient = new LocationClient(getActivity(), this, this);	
		mLocationClient.connect();
	}

	private void addFeaturesToMap() {
		print("addFeaturesToMap");
		List<MapFeature> l = features.getMapFeatures();
		for (MapFeature mf : l) {
			mMap.addMarker(MakeMapMarkerOptions(mf.getPosition(),mf.getName(),mf.getDescription(),mf.getFeatureType().getMarkerIconFileName()));
		}

		List<MapFeature> lu = features.getUserFeatureList();
		for (MapFeature mf : lu) {
			mMap.addMarker(MakeMapMarkerOptions(mf.getPosition(),mf.getName(),mf.getDescription(),mf.getFeatureType().getMarkerIconFileName()));
		}
	}

	private MarkerOptions MakeMapMarkerOptions(LatLng position,String title,String description,String markerIconFileName) {	
		print("MakeMapMarkerOptions");
		String fileName = markerIconFileName.toLowerCase(Locale.ENGLISH);
		BitmapDescriptor bd = AndroidTools.assetExists(getActivity(), MARKER_ICON_DIRECTORY + fileName) ?
				BitmapDescriptorFactory.fromAsset(MARKER_ICON_DIRECTORY + fileName) :
					BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA);
				
				BitmapDescriptor snippetBD = BitmapDescriptorFactory.fromAsset(MARKER_ICON_DIRECTORY + "snippeticon.png");

				MarkerOptions mo = new MarkerOptions()
				.position(position)
				.title(title)
				.snippet(description).icon(snippetBD)
				.icon(bd);
				return mo;
	}

	@Override
	public void onMapLongClick(LatLng arg0) {
		print("onMapLongClick");
		Geocoder g = new Geocoder(this.getActivity());
		List<Address> l = null;
		try {
			l = g.getFromLocation(arg0.latitude,arg0.longitude,1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		addUserFeatureMarkerToMap(new MarkerOptions()
		.position(arg0)
		.title(l.get(0).getAddressLine(0)));
		//		addMarkerToMap(new MarkerOptions()
		//		.position(arg0)
		//		.title("here"));	
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		print("onConnectionFailed");
	}

	@Override
	public void onConnected(Bundle arg0) {
		print("onConnected");
		mCurrentLocation = mLocationClient.getLastLocation();
		myLocation = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation,10f));
		updateMyLocationMarkerIcon();
	}

	private void updateMyLocationMarkerIcon() {
		print("updateMyLocationMarkerIcon");
		addMarkerToMap(MakeMapMarkerOptions(myLocation, "You are here!", "Happy dance!", "mylocation.png"));
	}

	private void addMarkerToMap(MarkerOptions mOptions) {
		print("addMarkerToMap");
		mMap.addMarker(mOptions);
		features.addFeature(new MapFeature(mOptions.getTitle(),mOptions.getPosition(),mOptions.getSnippet(),new MapFeatureType("useradded")));
	}

	private void addUserFeatureMarkerToMap(MarkerOptions mOptions) {
		print("addUserFeatureMarkerToMap");
		mMap.addMarker(mOptions);
		features.addUserFeature(new MapFeature(mOptions.getTitle(),mOptions.getPosition(),mOptions.getSnippet(),new MapFeatureType("useradded")));
	}

	@Override
	public void onDisconnected() {
		print("onDisconnected");
	}

	public MapFeatureManager getFeatures() {
		print("getFeatures");
		return features;
	}

	//	private GoogleMapOptions makeGoogleMapOptions() {
	//		GoogleMapOptions gMapOptions = new GoogleMapOptions();
	//		gMapOptions.mapType(GoogleMap.MAP_TYPE_SATELLITE)
	//		.compassEnabled(true)
	//		.rotateGesturesEnabled(true);
	//		return gMapOptions;
	//	}

	public void print(String message) {		
		if (((MapApp)this.getActivity().getApplication()).getSettings(PREF_KEY_NAMES.PRINTLOG, "false").equals("true")) {
			String logName = this.getClass().getSimpleName() + " ";
			AndroidTools.print(logName + message);
		}
	}

}
