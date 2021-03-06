package com.accenture.flight.model;

import lombok.*;

@Getter
@Setter
@Builder(access = AccessLevel.PUBLIC)
public class Airport {
    private int id;
    private String ident;
    private String type;
    private String name;
    private String latitude_deg;
    private String longitude_deg;
    private int elevation_ft;
    private String continent;
    private String iso_country;
    private String iso_region;
    private String municipality;
    private byte scheduled_service;
    private String gps_code;
    private String iata_code;
    private String local_code;
    private String home_link;
    private String wikipedia_link;
    private String keywords;
}
