package com.example.android_projects;

public class DistanceCalculator {

    public static double distance(String location1, String location2) {
        if (location1 == null || location2 == null)
            return 0;

        String[] latLon1 = location1.split(",");
        String[] latLon2 = location2.split(",");
        double lat1 = Double.parseDouble(latLon1[0]);
        double lon1 = Double.parseDouble(latLon1[1]);
        double lat2 = Double.parseDouble(latLon2[0]);
        double lon2 = Double.parseDouble(latLon2[1]);

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;

        return dist;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
