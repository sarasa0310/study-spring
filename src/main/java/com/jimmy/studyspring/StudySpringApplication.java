package com.jimmy.studyspring;

import com.jimmy.studyspring.relationaldataaccess.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class StudySpringApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(StudySpringApplication.class, args);
    }

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE customers" +
                "(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        List<Object[]> splitUpNames = Stream.of(
                "John Woo", "Jeff Dean", "Josh Bloch", "Josh Long"
                )
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        splitUpNames.forEach(name ->
                log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) " +
                "VALUES (?, ?)", splitUpNames);

        log.info("Querying for customer records where first_name = 'Josh':");

        jdbcTemplate.query(
                "SELECT id, first_name, last_name " +
                "FROM customers " +
                "WHERE first_name = ?", new Object[] {
                        "Josh"},
                (rs, rowNum) -> new Customer(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"))
        ).forEach(customer -> log.info(customer.toString()));
    }

}
