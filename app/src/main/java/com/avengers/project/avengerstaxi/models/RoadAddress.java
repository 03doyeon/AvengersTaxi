package com.avengers.project.avengerstaxi.models;

import com.google.api.client.util.Key;

public class RoadAddress {
    @Key("address_name") public String addressName;
    @Key("building_name") public String buildingName;
}
