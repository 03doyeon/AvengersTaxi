package com.avengers.project.avengerstaxi.location;

<<<<<<< HEAD
import android.os.Handler;

import com.avengers.project.avengerstaxi.models.AddressRequester;
=======
import android.location.Location;
import android.location.LocationManager;
>>>>>>> c78c61307c65a0ce62e4acf57d608fc702909086

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class MapEventListener implements MapView.MapViewEventListener {
<<<<<<< HEAD
    //handler가 필요
    private Handler handler;
    public MapEventListener(Handler handler){ this.handler=handler;}
=======

>>>>>>> c78c61307c65a0ce62e4acf57d608fc702909086
    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
<<<<<<< HEAD


        //1번 마커를 지우거나 옮기는 작업
        //2번 마커의 위치를 변경함(지도는 제공)
=======
        // 마커를 지우거나 옮기는 작업
        //마커의 위치 변경만(지도는 제공함)
>>>>>>> c78c61307c65a0ce62e4acf57d608fc702909086
        mapView.removeAllPOIItems();
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("Default Marker");
        marker.setTag(0);
        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker);
<<<<<<< HEAD

=======
>>>>>>> c78c61307c65a0ce62e4acf57d608fc702909086
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
<<<<<<< HEAD
       // Double latitude= mapPoint.getMapPointGeoCoord().latitude;
       // Double longitude= mapPoint.getMapPointGeoCoord().longitude;
        Thread request = new Thread(new AddressRequester(mapPoint.getMapPointGeoCoord().latitude, mapPoint.getMapPointGeoCoord().longitude,this.handler));
        request.start();
=======

>>>>>>> c78c61307c65a0ce62e4acf57d608fc702909086
    }
}
