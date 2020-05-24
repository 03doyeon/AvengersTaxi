package com.avengers.project.avengerstaxi.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class MapLocationListener implements LocationListener {

    private MapView mapView;


    @Override
    public void onLocationChanged(Location location) {
        location.getLatitude();
        location.getLongitude();
        //지도에 마커 추가 + 중심점 이동
        //this.mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(location.getLatitude(),location.getLongitude()),true); // 중심점 이동 완료

        if (location != null) {

            //this.mapView.removeAllPOIItems();

            mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(location.getLatitude(), location.getLongitude()), true);
            MapPOIItem marker = mapView.findPOIItemByTag(0);
            marker.setMapPoint(MapPoint.mapPointWithGeoCoord(location.getLatitude(), location.getLongitude()));


//            MapPOIItem marker = new MapPOIItem();
//            marker.setItemName("Default Marker");
//            marker.setTag(0);
//            marker.setMapPoint(MapPoint.mapPointWithGeoCoord(location.getLatitude(), location.getLongitude()));
//            marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
//            marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
//
//            mapView.addPOIItem(marker);

        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public  void  setMapView(MapView mapView){
        this.mapView = mapView;
    }
}
