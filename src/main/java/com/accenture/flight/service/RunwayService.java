package com.accenture.flight.service;

import com.accenture.flight.model.Airport;
import com.accenture.flight.model.Country;
import com.accenture.flight.model.Runway;
import com.accenture.flight.repository.RunwayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RunwayService {
    RunwayRepository runwayRepository;
    CountryService countryService;
    AirportService airportService;

    @Autowired
    public RunwayService(RunwayRepository runwayRepository, CountryService countryService, AirportService airportService) {
        this.runwayRepository = runwayRepository;
        this.countryService = countryService;
        this.airportService = airportService;
    }

    public List<Runway> retrieveRunwayByCountryCode(String countryCode) {
        return runwayRepository.retrieveRunwayByCountryCodeRepo(countryCode);

//        List<Runway> resultList = new ArrayList<>();
//        List<Country> countryList = countryService.retrieveCountryByCode(cCode);
//        if (countryList.size() == 0)
//            return null;
//        else {
//            List<Airport> airportList = airportService.retrieveAirportListByIsoCountry(countryList.get(0).getCode());
//            for (Airport airport : airportList) {
//                List<Runway> runwayList = runwayRepository.retrieveRunwayByAirportRef(airport.getId());
//                resultList.addAll(runwayList);//TODO if list is empty, what will be the result?
//            }
//            return resultList;
//        }

    }
}
