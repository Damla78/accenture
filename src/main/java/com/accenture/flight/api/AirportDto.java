package com.accenture.flight.api;

import com.accenture.flight.model.Airport;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder(access = AccessLevel.PUBLIC)
public class AirportDto {
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
