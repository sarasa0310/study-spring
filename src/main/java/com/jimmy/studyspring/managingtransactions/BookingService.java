package com.jimmy.studyspring.managingtransactions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookingService {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void book(String... people) {
        for (String person : people) {
            log.info("Booking " + person + " in a seat...");
            jdbcTemplate.update("INSERT INTO bookings(first_name) VALUES (?)", person);
        }
    }

    public List<String> findAllBookings() {
        return jdbcTemplate.query("SELECT first_name FROM bookings",
                (rs, rowNum) -> rs.getString("first_name"));
    }

}
