package com.accenture.flight.service;

import com.accenture.flight.model.Country;
import com.accenture.flight.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@Service
public class CountryService {
    private static final Logger logger = LoggerFactory.getLogger(CountryService.class);
    private static String COMMA_DELIMITER = ",";

    CountryRepository countryRepository;

    @Autowired
    ResourceLoader resourceLoader;

//    @Autowired
//    public CountryService(CountryRepository countryRepository)  {
//        this.countryRepository = countryRepository;
//        //recordCountries();
//    }


    public File getCountryFile() throws FileNotFoundException {

        try {
            return ResourceUtils.getFile("classpath:data/countries.csv");
        } catch (FileNotFoundException ex) {
            logger.error("Countries.csv couldn't be opened. " + ex);
            throw new FileNotFoundException("Countries.csv couldn't be opened. " + ex);
        }
    }

    public void recordCountries() throws IOException {
        String countryFile = new String(Files.readAllBytes(getCountryFile().toPath()));
        String[] countryLine = countryFile.split("\n");
        Arrays.stream(countryLine).forEach(cLine -> {
            String[] countryInfo = cLine.split(",");
            Country newCountry = Country.builder()
                    .id(Integer.parseInt(countryInfo[0]))
                    .code(countryInfo[1])
                    .name(countryInfo[2])
                    .continent(countryInfo[3])
                    .wikipedia_link(countryInfo[4])
                    .keywords(countryInfo.length == 6 ? countryInfo[5] : "").build();
            countryRepository.storeCountry(newCountry);
        });

    }

    public List<Country> listAllCountries() throws IOException {
        //String s = new String(Files.readAllBytes(getCountryFile().toPath()));
        //logger.info( );


        return null;
    }
//        InputStream is = getClass().getClassLoader().getResourceAsStream("countries.csv");
//
//        try (BufferedReader br = new BufferedReader(new FileReader("countries.csv"))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(COMMA_DELIMITER);
//                logger.info(Arrays.asList(values).get(0));
//            }
//        }catch (IOException ex){
//            logger.error("error reading in countries.csv "+ex);
//        }
//    }

}


