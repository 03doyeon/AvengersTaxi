package com.avengers.project.avengerstaxi;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avengers.project.avengerstaxi.location.MapEventListener;
import com.avengers.project.avengerstaxi.models.AddressModel;
import com.avengers.project.avengerstaxi.models.DisplayItem;
import com.avengers.project.avengerstaxi.models.Documents;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_LOCATION = 10001;
    private LocationManager locationManager;
   // private MapLocationListener locationListener;
    private MapEventListener mapEventListener;

    public MainActivity() {
        //this.locationListener = new MapLocationListener(); //생성자
        this.mapEventListener=new MapEventListener(makeHandler());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 안드로이드에서 권한 확인이 의무화 되어서 작성된 코드! 개념만 이해
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_LOCATION);
                return;
            }
        }
        this.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0.01f, this.locationListener);


        MapView mapView = new MapView(this); //세터(?)
        //this.locationListener.setMapView(mapView);//지도 전달

        Location loc = this.locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        mapView.setMapViewEventListener(this.mapEventListener);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        if (loc != null) {
            mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(loc.getLatitude(), loc.getLongitude()), true);

            MapPOIItem marker = new MapPOIItem();
            marker.setItemName("Default Marker");
            marker.setTag(0);
            marker.setMapPoint(MapPoint.mapPointWithGeoCoord(loc.getLatitude(), loc.getLongitude()));
            marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
            marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

            mapView.addPOIItem(marker);
        }

    }

    private Handler makeHandler() {
        return new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                TextView address=(TextView)findViewById(R.id.address);
                TextView latitude=(TextView) findViewById(R.id.latitude);
                TextView longitude=(TextView) findViewById(R.id.longitude);
                //AddressModel addressModel(AddressModel)msg.obj;
                //위도 경도 출력
                DisplayItem displayItem = (DisplayItem)msg.obj;
                AddressModel addressModel=displayItem.addressModel;
                latitude.setText(displayItem.latitude.toString());
                longitude.setText(displayItem.longitude.toString());

                //방어할수 있는 코드
                if(addressModel.documents.size()>0){
                    Documents document=addressModel.documents.get(0);
                    if(document.roadAddress!=null){
                        address.setText(document.roadAddress.addressName);
                        if(document.roadAddress.buildingName!=null){
                            address.setText(document.roadAddress.buildingName);
                        }else{
                            address.setText(document.address.AddressName);
                        }
                    }
                    address.setText(addressModel.documents.get(0).roadAddress.buildingName);
                    address.setText(addressModel.documents.get(0).roadAddress.addressName);
                }

            }
        };
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 권한 승인이 된 경우 다시 그리기
                    recreate();
                } else {
                    // 권한 승인이 안 된 경우 종료
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
