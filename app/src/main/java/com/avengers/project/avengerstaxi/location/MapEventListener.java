package com.avengers.project.avengerstaxi.location;

import android.os.Handler;

import com.avengers.project.avengerstaxi.models.AddressRequester;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class MapEventListener implements MapView.MapViewEventListener {
    //handler가 필요
    private Handler handler;
    public MapEventListener(Handler handler){ this.handler=handler;}
    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {


        //1번 마커를 지우거나 옮기는 작업
        //2번 마커의 위치를 변경함(지도는 제공)
        mapView.removeAllPOIItems();
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("Default Marker");
        marker.setTag(0);
        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker);

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
       // Double latitude= mapPoint.getMapPointGeoCoord().latitude;
       // Double longitude= mapPoint.getMapPointGeoCoord().longitude;
        Thread request = new Thread(new AddressRequester(mapPoint.getMapPointGeoCoord().latitude, mapPoint.getMapPointGeoCoord().longitude,this.handler));
        request.start();
    }
}
