DROP DATABASE IF EXISTS flightdb;
CREATE DATABASE flightdb;
USE flightdb;
CREATE TABLE COUNTRY (
    id INT NOT NULL,
    code VARCHAR(10),
    name VARCHAR(60),
    continent VARCHAR(10),
    wikipedia_link VARCHAR(500),
    keywords VARCHAR(500),
    PRIMARY KEY(id)
);
CREATE TABLE AIRPORT(
    id INT NOT NULL,
    ident VARCHAR(10),
    type VARCHAR(50),
    name VARCHAR(250),
    latitude_deg VARCHAR(25),
    longitude_deg VARCHAR(25),
    elevation_ft INT,
    continent VARCHAR(10),
    iso_country VARCHAR(10),
    iso_region VARCHAR(10),
    municipality VARCHAR(60),
    scheduled_service BIT,
    gps_code VARCHAR(10),
    iata_code VARCHAR(10),
    local_code VARCHAR(10),
    home_link VARCHAR(500),
    wikipedia_link VARCHAR(500),
    keywords VARCHAR(500),
    PRIMARY KEY(id)
);
328041,
40554,
"TT-TT01",
4301,
50,
"RWY 10 is Smooth Asphalt for 2670' then Rough Asphalt and Grass",
0,0,"10",10.2534,61.2643,60,104,140,"28",10.2532,61.2622,60,284,323
CREATE TABLE RUNWAY(
    id INT NOT NULL,
    airport_ref INT,
    airport_ident VARCHAR(15),
    length_ft INT,
    width_ft INT,
    surface VARCHAR(250),
    lighted BIT,
    closed BIT,
    le_ident VARCHAR(15),
    le_latitude_deg VARCHAR(25),
    le_longitude_deg VARCHAR(25),
    le_elevation_ft INT,
    le_heading_degT VARCHAR(15),
    le_displaced_threshold_ft DECIMAL,
    he_ident VARCHAR(15),
    he_latitude_deg VARCHAR(25),
    he_longitude_deg VARCHAR(25),
    he_elevation_ft INT,
    he_heading_degT DECIMAL,
    he_displaced_threshold_ft INT,
    PRIMARY KEY(id)
);
