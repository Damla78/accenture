package com.accenture.flight.repository;

import com.accenture.flight.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class CountryRepository {
    private static final Logger logger = LoggerFactory.getLogger(CountryRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int storeCountry(Country country) {
        int statusRes = 0;
        try {
            statusRes = jdbcTemplate.update("INSERT INTO COUNTRY(id, code, name, continent, wikipedia_link, keywords) VALUES (?,?,?,?,?,?)",
                    country.getId(), country.getCode(), country.getName(), country.getContinent(), country.getWikipedia_link(), country.getKeywords());
        } catch (DataAccessException e) {
            logger.error("Problem for data access in Country. Country.ID="+country.getId() );
            e.printStackTrace();
        } finally {
            try {
                jdbcTemplate.getDataSource().getConnection().close();
            } catch (SQLException e) {
                logger.error("While in closing datasource connection for "+country.getName()+ " problem occured.");
                e.printStackTrace();
            }
        }
        return statusRes;
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

