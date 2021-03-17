package com.accenture.flight.service;

import com.accenture.flight.model.Airport;
import com.accenture.flight.model.Country;
import com.accenture.flight.model.Topten;
import com.accenture.flight.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {
    AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository){
        this.airportRepository = airportRepository;
    }
    public List<Airport> retrieveAirportListByIsoCountry(String countryCode){
        return airportRepository.retrieveAirportsByCountryCode(countryCode);
    }
    public List<Topten> getTopTenAirportsByCountry(){
        return airportRepository.retrieveTopTen();
    }
}
