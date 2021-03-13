package com.accenture.flight.repository;

import com.accenture.flight.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class CountryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int storeCountry(Country country){
        return jdbcTemplate.update( "INSERT INTO COUNTRY(id, code, name, continent, wikipedia_link, keywords) VALUES (?,?,?,?,?,?)",
                country.getId(), country.getCode(), country.getName(), country.getContinent(), country.getWikipedia_link(), country.getKeywords());
    }

//    public List<Country> getCountries() {
//        List<Country> countryList = jdbcTemplate.query(
//                "SELECT * FROM country WHERE name='?'",
//                (rs, rowNum) -> new Person(rs.getString("id"),
//                        rs.getString("name"), Dates.stringToDate(rs.getString("birthDate")),
//                        rs.getString("city"))
//        );
//        return personList;
//    }
}

