package com.accenture.flight.api;

import com.accenture.flight.model.Airport;
import com.accenture.flight.model.Country;
import com.accenture.flight.model.Runway;
import com.accenture.flight.service.AirportService;
import com.accenture.flight.service.CountryLoad;
import com.accenture.flight.service.CountryService;
import com.accenture.flight.service.RunwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class FlightController {
    CountryService countryService;
    AirportService airportService;
    RunwayService runwayService;
    private Model foundModel = null;

    @Autowired
    public FlightController(CountryService countryService, AirportService airportService, RunwayService runwayService) {
        this.countryService = countryService;
        this.airportService = airportService;
        this.runwayService = runwayService;
    }

    @GetMapping("/index")
    public String viewFlightForm(Model model) {
        List<CountryDto> countryDtoList = countryService.listAllCountryDtos();
        model.addAttribute("countryDtoList", countryDtoList);
        model.addAttribute("countryDto", Country.builder().build());
        return "index";
    }

    @GetMapping("/foundrunway")
    public String viewRunway(Model model) {
        model = foundModel;
        return "foundRunway";
    }

    @PostMapping("/index")
    public String viewFlightSubmit(@ModelAttribute("countryDto") CountryDto countryDto, Model model) {
        //model.addAttribute("foundCountry", Country.builder().build());//TODO Do I need dummy object?
        //model.addAttribute("foundAirportList", Airport.builder().build());
        //model.addAttribute("foundRunway", Runway.builder().build());

        List<Country> countryList = null;
        if (countryDto.code != null && !countryDto.code.equals("")) {
            countryList = countryService.retrieveCountryByCode(countryDto.code);
            //searchByCode(countryDto.code);
        } else if (countryDto.name != null) {
            countryList = countryService.retrieveCountryByName(countryDto.name);
            //searchByName(countryDto.name);
        }
        if (countryList != null && countryList.size() > 0) {
            searchRunways(countryList.get(0), model);
        }
        return "foundRunway";//"redirect:index";
    }

    public void searchRunways(Country country, Model model) {
        List<Airport> airportList;
        List<Runway> runwayList;

        model.addAttribute("foundCountry", country);
        airportList = airportService.retrieveAirportListByIsoCountry(country.getCode());
        if (airportList.size() != 0) {
            model.addAttribute("foundAirportList", airportList);
            runwayList = runwayService.retrieveRunwayByCountryCode(country.getCode());
            if (runwayList.size() != 0) {
                model.addAttribute("foundRunway", runwayList);
            }
        }
        foundModel = model;
    }
}

