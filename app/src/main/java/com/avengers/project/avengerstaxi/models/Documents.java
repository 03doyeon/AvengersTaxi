package com.avengers.project.avengerstaxi.models;

import com.google.api.client.util.Key;

public class Documents {
    @Key("road_address") public RoadAddress roadaddress;
    //@Key() public Address address;
    @Key("road_address") public Road_address roadAddress;
    @Key("address") public Address address;
}
