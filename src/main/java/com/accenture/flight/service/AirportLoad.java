package com.accenture.flight.service;

import com.accenture.flight.model.Airport;
import com.accenture.flight.repository.AirportRepository;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class AirportLoad implements LoadService {
    private static final Logger logger = LoggerFactory.getLogger(AirportLoad.class);
    private static String PATH = "data/airports.csv";
    AirportRepository airportRepository;

    @Autowired
    public AirportLoad(AirportRepository airportRepository) throws IOException {
        this.airportRepository = airportRepository;
    }

    @Override
    public void recordDatas() throws IOException {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(PATH);
        String airportFile = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8.name());
        String[] airportLine = airportFile.split(LINE_DELIMITER);

        Arrays.stream(airportLine).forEach(aLine -> {
            ArrayList<String> airportInfo = divideLine(aLine);

            if (!aLine.startsWith("\"id\"")) {
                try {
                    Airport newAirport = Airport.builder()
                            .id(Integer.parseInt(airportInfo.get(0)))
                            .ident(!airportInfo.get(1).equals("") ? airportInfo.get(1).toUpperCase() : "")
                            .type(!airportInfo.get(2).equals("") ? airportInfo.get(2) : "")
                            .name(!airportInfo.get(3).equals("") ? airportInfo.get(3) : "")
                            .latitude_deg(!airportInfo.get(4).equals("") ? airportInfo.get(4) : "")
                            .longitude_deg(!airportInfo.get(5).equals("") ? airportInfo.get(5) : "")
                            .elevation_ft(!airportInfo.get(6).equals("") ? Integer.parseInt(airportInfo.get(6)) : 0)
                            .continent(!airportInfo.get(7).equals("") ? airportInfo.get(7) : "")
                            .iso_country((airportInfo.size() > 8 && !airportInfo.get(8).equals("")) ? airportInfo.get(8).toUpperCase() : "")
                            .iso_region((airportInfo.size() > 9 && !airportInfo.get(9).equals("")) ? airportInfo.get(9) : "")
                            .municipality((airportInfo.size() > 10 && !airportInfo.get(10).equals("")) ? airportInfo.get(10) : "")
                            .scheduled_service((airportInfo.size() > 11 && !airportInfo.get(11).equals("")) ?
                                    (airportInfo.get(11).equalsIgnoreCase("no") ? 0 : (byte) 1) : 0)
                            .gps_code((airportInfo.size() > 12 && !airportInfo.get(12).equals("")) ? airportInfo.get(12) : "")
                            .iata_code((airportInfo.size() > 13 && !airportInfo.get(13).equals("")) ? airportInfo.get(13) : "")
                            .local_code((airportInfo.size() > 14 && !airportInfo.get(14).equals("")) ? airportInfo.get(14) : "")
                            .home_link((airportInfo.size() > 15 && !airportInfo.get(15).equals("")) ? airportInfo.get(15) : "")
                            .wikipedia_link((airportInfo.size() > 16 && !airportInfo.get(16).equals("")) ? airportInfo.get(16) : "")
                            .keywords((airportInfo.size() > 17 && !airportInfo.get(17).equals("")) ? airportInfo.get(17) : "")
                            .build();
                    airportRepository.storeAirport(newAirport);
                } catch (Exception e) {
                    logger.error("**** Creating airport data occured exception: " + e);
                }
            }
        });
        logger.info("?*** AirportLoad is complete successfully");
    }


}
