package com.accenture.flight.api;

import com.accenture.flight.model.Country;
import com.accenture.flight.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class Flight {
    CountryService countryService;

    @Autowired
    public Flight(CountryService countryService){
        this.countryService = countryService;
    }

    @GetMapping("/index")
    public String viewPersonForm(Model model){
        try {
            List<Country> countryList = countryService.listAllCountries();
            model.addAttribute("countryDtoList", countryList);
        }catch (IOException ex){
            return "error";
        }


        return "index";
    }
}

