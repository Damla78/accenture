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
import java.util.List;

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
                    airport.getLongitude_deg(), airport.getElevation_ft(), airport.getContinent(), airport.getIso_country(),
                    airport.getIso_region(), airport.getMunicipality(), airport.getScheduled_service(), airport.getGps_code(),
                    airport.getIata_code(), airport.getLocal_code(), airport.getHome_link(), airport.getWikipedia_link(),
                    airport.getKeywords());
        } catch (DataAccessException e) {
            logger.error("Problem for data access in Airport. Airport.ID=" + airport.getId());
            e.printStackTrace();
        } finally {
            try {
                jdbcTemplate.getDataSource().getConnection().close();
            } catch (SQLException e) {
                logger.error("While in closing datasource connection for " + airport.getName() + " problem occured.");
                e.printStackTrace();
            }
        }

        return statusRes;
    }

    public List<Airport> retrieveAirportsByCountryCode(String cCode) {
        String sql = "select * from airport where iso_country='" + cCode + "'";
        return jdbcTemplate.query(sql,
                (rs, rownum) -> Airport.builder()
                        .id(rs.getInt("id"))
                        .ident(rs.getString("ident"))
                        .type(rs.getString("type"))
                        .name(rs.getString("name"))
                        .latitude_deg(rs.getString("latitude_deg"))
                        .longitude_deg(rs.getString("longitude_deg"))
                        .elevation_ft(rs.getInt("elevation_ft"))
                        .continent(rs.getString("continent"))
                        .iso_country(rs.getString("iso_country"))
                        .iso_region(rs.getString("iso_region"))
                        .municipality(rs.getString("municipality"))
                        .scheduled_service(rs.getByte("scheduled_service"))
                        .gps_code(rs.getString("gps_code"))
                        .iata_code(rs.getString("iata_code"))
                        .local_code(rs.getString("local_code"))
                        .home_link(rs.getString("home_link"))
                        .wikipedia_link(rs.getString("wikipedia_link"))
                        .keywords(rs.getString("keywords"))
                        .build()
        );
    }
}

