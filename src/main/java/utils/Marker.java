package utils;

public class Marker {
	public Marker(double lat, double lng, int count) {
		this.lat = lat;
		this.lng = lng;
		this.count = count;
	}

	public Marker() {
	};

	double lat;

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	double lng;

	int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
