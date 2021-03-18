package com.accenture.flight.api;

import com.accenture.flight.model.Airport;
import com.accenture.flight.model.Country;
import com.accenture.flight.model.Runway;
import com.accenture.flight.model.Topten;
import com.accenture.flight.repository.CountryRepository;
import com.accenture.flight.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class FlightController {
    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);
    CountryService countryService;
    AirportService airportService;
    RunwayService runwayService;
    CountryLoad countryLoad;
    AirportLoad airportLoad;
    RunwayLoad runwayLoad;
    private Model foundModel = null;

    @Autowired
    public FlightController(CountryService countryService, AirportService airportService, RunwayService runwayService,
                            CountryLoad countryLoad, AirportLoad airportLoad, RunwayLoad runwayLoad) {
        this.countryService = countryService;
        this.airportService = airportService;
        this.runwayService = runwayService;
        this.countryLoad = countryLoad;
        this.airportLoad = airportLoad;
        this.runwayLoad = runwayLoad;
    }

    @GetMapping("/load")
    public String viewLoad(Model model) {
        return "load";
    }

    @PostMapping("/load")
    public void loadData(Model model) {
        try {
            countryLoad.recordDatas();
            airportLoad.recordDatas();
            runwayLoad.recordDatas();
        } catch (IOException exception) {
            logger.error("Error in load process: " + exception);
        }

    }

    @PostMapping("/topten")
    public String viewTopTen(Model model) {
        List<Topten> topTenAirportsByCountry = airportService.getTopTenAirportsByCountry();
        model.addAttribute("topTenDtoList", topTenAirportsByCountry);
        return "topten";
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
        return "foundrunway";
    }

    @PostMapping("/index")
    public String viewFlightSubmit(@ModelAttribute("countryDto") CountryDto countryDto, Model model) {

        List<Country> countryList = null;
        if (countryDto.code != null && !countryDto.code.equals("")) {
            countryList = countryService.retrieveCountryByCode(countryDto.code);
        } else if (countryDto.name != null) {
            countryList = countryService.retrieveCountryByName(countryDto.name);
        }
        if (countryList != null && countryList.size() > 0) {
            searchRunways(countryList.get(0), model);
        }
        return "foundrunway";
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
                model.addAttribute("foundrunway", runwayList);
            }
        }
        foundModel = model;
    }
}

