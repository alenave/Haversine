package com.adatech.cluster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Config;
import utils.Haversine;
import utils.Marker;
import utils.Request;

public class App {
	public static List<Marker> markers = new ArrayList<Marker>();
	public static Map<String, Marker> clusters = new HashMap<String, Marker>();

	public static void main(String[] args) {
		double lat = 22.3579355;
		double lng = 113.9809289;
		int markerCount = 1000000;
		for (int i = 0; i < markerCount; i++) {
			double offset = i / 900000.0;
			double lat_ = lat + offset;
			double lng_ = lng + offset;
			markers.add(new Marker(lat_, lng_, 0));
		}
		int key = 1;
		for (int i = 0; i < markers.size(); i++) {
			int clusterCounter = 0;
			for (int j = i + 1; j < markers.size() - 1; j++) {
				if (Haversine.haversine(markers.get(i).getLat(), markers.get(i).getLng(), markers.get(j).getLat(),
						markers.get(j).getLng()) <= 5) {
					clusterCounter++;
				} else {
					markers.get(i).setCount(clusterCounter);
					clusters.put("-" + String.valueOf(key++), markers.get(i));
					i = j++;
					break;
				}
			}
		}
		new Request().post(clusters, Config.END_POINT);
		System.out.println(key);
	}
}
