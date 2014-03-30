package chis.dip.model;

import com.google.android.gms.maps.model.LatLng;

public class MapFeature {

	private long id;
	private String name;
	private LatLng position;
	private String description;
	private MapFeatureType featureType;

	private double lat;
	private double lng;
	
	private String postcode;
	
	private final char SEPERATOR = ',';

	public MapFeature() {

	}

	public MapFeature(
			String name, 
			LatLng position, 
			String description, 
			MapFeatureType featureType) {

		setName(name);
		setPosition(position);
		setDescription(description);
		setFeatureType(featureType);
		setPostcode("");

	}
	
	public String toSerialiseString() {
		String s = null;
		s = name + SEPERATOR +
				lat + SEPERATOR +
				lng + SEPERATOR +
				postcode + SEPERATOR + 
				description + SEPERATOR +
				featureType.getName();
		return s;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public LatLng getPosition() {
		return position;
	}


	public void setPosition(LatLng position) {
		this.position = position;
		this.lat = position.latitude;
		this.lng = position.longitude;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public MapFeatureType getFeatureType() {
		return featureType;
	}


	public void setFeatureType(MapFeatureType featureType) {
		this.featureType = featureType;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((featureType == null) ? 0 : featureType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapFeature other = (MapFeature) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (featureType == null) {
			if (other.featureType != null)
				return false;
		} else if (!featureType.equals(other.featureType))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "MapFeature [name=" + name + ", position=" + position
				+ ", description=" + description + ", featureType="
				+ featureType + "]";
	}


	public double getLat() {
		return lat;
	}


	public void setLat(double lat) {
		this.lat = lat;
		Double d = position.longitude;
		position = new LatLng(lat,d);
	}


	public double getLng() {
		return lng;
	}


	public void setLng(double lng) {
		this.lng = lng;
		Double d = position.latitude;
		position = new LatLng(d,lng);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	



}
