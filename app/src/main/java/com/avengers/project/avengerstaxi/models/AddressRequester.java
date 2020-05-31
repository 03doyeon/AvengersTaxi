package com.avengers.project.avengerstaxi.models;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

public class AddressRequester implements Runnable {
    private Handler handler;
    private Double lat;
    private Double lng;

    public AddressRequester(double lat, double lng, Handler handler) {
        this.lat = lat;
        this.lng = lng;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory(
                    new HttpRequestInitializer() {
                        @Override
                        public void initialize(HttpRequest request) {
                            request.setParser(new JsonObjectParser(new JacksonFactory()));
                        }
                    });
            String urlString = String.format("https://dapi.kakao.com/v2/local/geo/coord2address.json?x=%s&y=%s", this.lng.toString(), this.lat.toString());
            GenericUrl url = new GenericUrl(urlString);
            HttpHeaders headers = new HttpHeaders();
            headers.setAuthorization("KakaoAK a0b9c1b2f417d6b1a9ca623d2393b2ad");//KakaoAK REST_API_KEY
            HttpRequest request = requestFactory.buildGetRequest(url).setHeaders(headers);

            AddressModel addressModel = request.execute().parseAs(AddressModel.class);

            DisplayItem displayItem=new DisplayItem();
            displayItem.addressModel=addressModel;
            displayItem.latitude=this.lat;
            displayItem.longitude=this.lng;
            Message message = this.handler.obtainMessage();
            message.obj = displayItem;
            this.handler.sendMessage(message);
        } catch (Exception ex) {
            Log.e("HTTP_REQUEST", ex.toString());
        }
    }
}
