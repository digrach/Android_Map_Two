package chis.dip.model;

public class MapFeatureType {
	
	private String name;
	private String markerIconFileName;
	
	public MapFeatureType(String name) {
		setName(name);
		setMarkerIconFileName(name + ".png");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMarkerIconFileName() {
		return markerIconFileName;
	}
	public void setMarkerIconFileName(String markerIconFileName) {
		this.markerIconFileName = markerIconFileName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((markerIconFileName == null) ? 0 : markerIconFileName
						.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		MapFeatureType other = (MapFeatureType) obj;
		if (markerIconFileName == null) {
			if (other.markerIconFileName != null)
				return false;
		} else if (!markerIconFileName.equals(other.markerIconFileName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MapFeatureType [name=" + name + ", markerIconFileName="
				+ markerIconFileName + "]";
	}
	
	
	
	
	

}
