package com.accenture.flight.repository;

import com.accenture.flight.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
            logger.error("Problem for data access in Country. Country.ID=" + country.getId());
            e.printStackTrace();
        } finally {
            try {
                jdbcTemplate.getDataSource().getConnection().close();
            } catch (SQLException e) {
                logger.error("While in closing datasource connection for " + country.getName() + " problem occured.");
                e.printStackTrace();
            }
        }
        return statusRes;
    }

    public List<Country> getCountry(int countryId) {
        String sql = "SELECT * FROM COUNTRY WHERE ID = " + countryId;
        return jdbcTemplate.query(sql,
                (rs, rownum) -> Country.builder()
                        .id(rs.getInt("id"))
                        .code(rs.getString("code"))
                        .name(rs.getString("name"))
                        .continent(rs.getString("continent"))
                        .wikipedia_link(rs.getString("wikipedia_link"))
                        .keywords(rs.getString("keywords")).build()
        );
    }

    public List<Country> getCountryList() {
        String sql = "SELECT * FROM COUNTRY";
        return jdbcTemplate.query(sql,
                (rs, rownum) -> Country.builder()
                        .id(rs.getInt("id"))
                        .code(rs.getString("code"))
                        .name(rs.getString("name"))
                        .continent(rs.getString("continent"))
                        .wikipedia_link(rs.getString("wikipedia_link"))
                        .keywords(rs.getString("keywords")).build()
        );
    }

    public List<Country> getCountryByCode(String cCode) {
        String sql = "SELECT * FROM COUNTRY WHERE CODE LIKE '" + cCode + "%'";
        return jdbcTemplate.query(sql,
                (rs, rownum) -> Country.builder()
                        .id(rs.getInt("id"))
                        .code(rs.getString("code"))
                        .name(rs.getString("name"))
                        .continent(rs.getString("continent"))
                        .wikipedia_link(rs.getString("wikipedia_link"))
                        .keywords(rs.getString("keywords")).build()
        );
    }

    public List<Country> getCountryByName(String cName) {
        String sql = "SELECT * FROM COUNTRY WHERE NAME LIKE '" + cName + "%'";
        return jdbcTemplate.query(sql,
                (rs, rownum) -> Country.builder()
                        .id(rs.getInt("id"))
                        .code(rs.getString("code"))
                        .name(rs.getString("name"))
                        .continent(rs.getString("continent"))
                        .wikipedia_link(rs.getString("wikipedia_link"))
                        .keywords(rs.getString("keywords")).build()
        );
    }
}
