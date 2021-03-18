package com.accenture.flight.service;

import com.accenture.flight.model.Airport;
import com.accenture.flight.model.Runway;
import com.accenture.flight.repository.AirportRepository;
import com.accenture.flight.repository.RunwayRepository;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class RunwayLoad implements LoadService {
    private static final Logger logger = LoggerFactory.getLogger(RunwayLoad.class);
    private static String PATH = "data/runways.csv";
    RunwayRepository runwayRepository;

    @Autowired
    public RunwayLoad(RunwayRepository runwayRepository) throws IOException {
        this.runwayRepository = runwayRepository;
    }

    @Override
    public void recordDatas() throws IOException {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(PATH);
        String runwayFile = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8.name());
        String[] runwayLine = runwayFile.split(LINE_DELIMITER);

        Arrays.stream(runwayLine).forEach(aLine -> {
            ArrayList<String> runwayInfo = divideLine(aLine);

            if (!aLine.startsWith("\"id\"")) {
                try {
                    Runway newRunway = Runway.builder()
                            .id(Integer.parseInt(runwayInfo.get(0)))
                            .airport_ref(Integer.parseInt(runwayInfo.get(1)))
                            .airport_ident(!runwayInfo.get(2).equals("") ? runwayInfo.get(2).toUpperCase() : "")
                            .length_ft(!runwayInfo.get(3).equals("") ? Integer.parseInt(runwayInfo.get(3)) : 0)
                            .width_ft(!runwayInfo.get(4).equals("") ? Integer.parseInt(runwayInfo.get(4)) : 0)
                            .surface(!runwayInfo.get(5).equals("") ? runwayInfo.get(5) : "")
                            .lighted(!runwayInfo.get(6).equals("") ? Byte.parseByte(runwayInfo.get(6)) : 0)
                            .closed(!runwayInfo.get(7).equals("") ? Byte.parseByte(runwayInfo.get(7)) : 0)
                            .le_ident((runwayInfo.size() > 8 && !runwayInfo.get(8).equals("")) ? runwayInfo.get(8) : "")
                            .le_latitude_deg((runwayInfo.size() > 9 && !runwayInfo.get(9).equals("")) ? runwayInfo.get(9) : "")
                            .le_longitude_deg((runwayInfo.size() > 10 && !runwayInfo.get(10).equals("")) ? runwayInfo.get(10) : "")
                            .le_elevation_ft((runwayInfo.size() > 11 && !runwayInfo.get(11).equals("")) ? Integer.parseInt(runwayInfo.get(11)) : 0)
                            .le_heading_degT((runwayInfo.size() > 12 && !runwayInfo.get(12).equals("")) ? runwayInfo.get(12) : "")
                            .le_displaced_threshold_ft((runwayInfo.size() > 13 && !runwayInfo.get(13).equals("")) ? Double.parseDouble(runwayInfo.get(13)) : 0.0)
                            .he_ident((runwayInfo.size() > 14 && !runwayInfo.get(14).equals("")) ? runwayInfo.get(14) : "")
                            .he_latitude_deg((runwayInfo.size() > 15 && !runwayInfo.get(15).equals("")) ? runwayInfo.get(15) : "")
                            .he_longitude_deg((runwayInfo.size() > 16 && !runwayInfo.get(16).equals("")) ? runwayInfo.get(16) : "")
                            .he_elevation_ft((runwayInfo.size() > 17 && !runwayInfo.get(17).equals("")) ? Integer.parseInt(runwayInfo.get(17)) : 0)
                            .he_heading_degT((runwayInfo.size() > 18 && !runwayInfo.get(18).equals("")) ? Double.parseDouble(runwayInfo.get(18)) : 0)
                            .he_displaced_threshold_ft((runwayInfo.size() > 19 && !runwayInfo.get(19).equals("")) ? Integer.parseInt(runwayInfo.get(19)) : 0)
                            .build();
                    runwayRepository.storeRunway(newRunway);
                } catch (Exception e) {
                    logger.info("**** Creating runway data occured exception: " + e);
                }
            }
        });
        logger.error("?*** RunwayLoad is complete successfully");
    }
}