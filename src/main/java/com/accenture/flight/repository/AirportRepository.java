package com.accenture.flight.repository;

import com.accenture.flight.model.Airport;
import com.accenture.flight.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class AirportRepository {
    private static final Logger logger = LoggerFactory.getLogger(AirportRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int storeAirport(Airport airport) {
        int statusRes = 0;
        try {
            statusRes = jdbcTemplate.update("INSERT INTO airport(id, ident, type, name, latitude_deg, longitude_deg," +
                            "elevation_ft, continent, iso_country, iso_region, municipality, scheduled_service, gps_code," +
                            "iata_code, local_code, home_link, wikipedia_link, keywords) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    airport.getId(), airport.getIdent(), airport.getType(), airport.getName(), airport.getLatitude_deg(),
                    airport.getLongitude_deg(),airport.getElevation_ft(),airport.getContinent(),airport.getIso_country(),
                    airport.getIso_region(), airport.getMunicipality(), airport.getScheduled_service(), airport.getGps_code(),
                    airport.getIata_code(), airport.getLocal_code(), airport.getHome_link(), airport.getWikipedia_link(),
                    airport.getKeywords());
        } catch (DataAccessException e) {
            logger.error("Problem for data access in Airport. Airport.ID="+airport.getId() );
            e.printStackTrace();
        } finally {
            try {
                jdbcTemplate.getDataSource().getConnection().close();
            } catch (SQLException e) {
                logger.error("While in closing datasource connection for "+airport.getName()+ " problem occured.");
                e.printStackTrace();
            }
        }

        return statusRes;


    }
}
