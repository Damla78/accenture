package com.accenture.flight.model;

import lombok.*;

@Getter
@Setter
@Builder(access = AccessLevel.PUBLIC)
public class Runway {
    private int id;
    private int airport_ref;
    private String airport_ident;
    private int length_ft;
    private int width_ft;
    private String surface;
    private byte lighted;
    private byte closed;
    private String le_ident;
    private double le_latitude_deg;
    private double le_longitude_deg;
    private int le_elevation_ft;
    private String le_heading_degT;
    private double le_displaced_threshold_ft;
    private double he_ident;
    private double he_latitude_deg;
    private double he_longitude_deg;
    private int he_elevation_ft;
    private int he_heading_degT;
    private int he_displaced_threshold_ft;
}
