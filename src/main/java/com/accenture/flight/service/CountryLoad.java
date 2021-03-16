package com.accenture.flight.service;

import com.accenture.flight.model.Country;
import com.accenture.flight.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Scope("singleton")
public class CountryLoad implements LoadService{
    private static final Logger logger = LoggerFactory.getLogger(CountryLoad.class);
    private static String PATH = "classpath:data/countries.csv";
    CountryRepository countryRepository;

    @Autowired
    public CountryLoad(CountryRepository countryRepository) throws IOException{//, ResourceLoader resourceLoader
        this.countryRepository = countryRepository;
            recordDatas();
    }

    public void recordDatas() throws IOException {
        String countryFile = new String(Files.readAllBytes(getFile(PATH).toPath()));
        String[] countryLine = countryFile.split(LINE_DELIMITER);
        Arrays.stream(countryLine).forEach(cLine -> {
            ArrayList<String> countryInfo = divideLine(cLine);
            if (!cLine.startsWith("\"id\"")) {
                Country newCountry = Country.builder()
                        .id(Integer.parseInt(countryInfo.get(0)))
                        .code(countryInfo.get(1))
                        .name(countryInfo.get(2))
                        .continent(countryInfo.get(3))
                        .wikipedia_link(countryInfo.get(4))
                        .keywords(countryInfo.size() == 6 ? countryInfo.get(5) : "").build();
                countryRepository.storeCountry(newCountry);
            }
        });
        logger.info("?*** CountrytLoad is complete successfully");
    }


    public List<Country> listAllCountries() throws IOException {
        //String s = new String(Files.readAllBytes(getCountryFile().toPath()));
        //logger.info( );


        return null;
    }

}


