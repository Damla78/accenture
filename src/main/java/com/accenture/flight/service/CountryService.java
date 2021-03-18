package com.accenture.flight.service;

import com.accenture.flight.api.CountryDto;
import com.accenture.flight.model.Country;
import com.accenture.flight.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryDto> listAllCountryDtos() {
        List<Country> countryList = countryRepository.getCountryList();
        List<CountryDto> countryDtoList = countryList.stream().map(country -> country.toCountryDto()).collect(Collectors.toList());
        ;
        return countryDtoList;
    }

    public List<Country> retrieveCountryByCode(String cCode) {
        return countryRepository.getCountryByCode(cCode);
    }

    public List<Country> retrieveCountryByName(String cName) {
        return countryRepository.getCountryByName(cName);
    }

}
