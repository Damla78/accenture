package com.accenture.flight.api;

import com.accenture.flight.model.Airport;
import com.accenture.flight.model.Runway;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder(access = AccessLevel.PUBLIC)
public class RunwayDto {
    private int id;
    private int airport_ref;
    private String airport_ident;
    private int length_ft;
    private int width_ft;
    private String surface;
    private byte lighted;
    private byte closed;
    private String le_ident;
    private String le_latitude_deg;
    private String le_longitude_deg;
    private int le_elevation_ft;
    private String le_heading_degT;
    private double le_displaced_threshold_ft;
    private String he_ident;
    private String he_latitude_deg;
    private String he_longitude_deg;
    private int he_elevation_ft;
    private double he_heading_degT;
    private int he_displaced_threshold_ft;
}
