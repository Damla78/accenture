package com.accenture.flight.repository;

import com.accenture.flight.model.Country;
import com.accenture.flight.model.Runway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class RunwayRepository {
    private static final Logger logger = LoggerFactory.getLogger(RunwayRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int storeRunway(Runway runway) {
        int statusRes = 0;
        try {
            statusRes = jdbcTemplate.update("INSERT INTO RUNWAY(id, airport_ref, airport_ident, length_ft, " +
                            "width_ft, surface,lighted,closed,le_ident,le_latitude_deg,le_longitude_deg,le_elevation_ft," +
                            "le_heading_degT,le_displaced_threshold_ft,he_ident,he_latitude_deg,he_longitude_deg," +
                            "he_elevation_ft,he_heading_degT,he_displaced_threshold_ft) VALUES " +
                            "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    runway.getId(),
                    runway.getAirport_ref(),
                    runway.getAirport_ident(),
                    runway.getLength_ft(),
                    runway.getWidth_ft(),
                    runway.getSurface(),
                    runway.getLighted(),
                    runway.getClosed(),
                    runway.getLe_ident(),
                    runway.getLe_latitude_deg(),
                    runway.getHe_longitude_deg(),
                    runway.getLe_elevation_ft(),
                    runway.getLe_heading_degT(),
                    runway.getLe_displaced_threshold_ft(),
                    runway.getHe_ident(),
                    runway.getHe_latitude_deg(),
                    runway.getHe_longitude_deg(),
                    runway.getHe_elevation_ft(),
                    runway.getHe_heading_degT(),
                    runway.getHe_displaced_threshold_ft()
            );
        } catch (DataAccessException e) {
            logger.error("**** Problem for data access in Runway. Runway.ID="+runway.getId() );
            e.printStackTrace();
        } finally {
            try {
                jdbcTemplate.getDataSource().getConnection().close();
            } catch (SQLException e) {
                logger.error("**** While in closing datasource connection for "+runway.getId()+ " problem occured.");
                e.printStackTrace();
            }
        }
        return statusRes;
    }
}