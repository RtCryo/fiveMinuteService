package com.example.fiveminuteservice;

import com.example.fiveminuteservice.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomerService {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Customer> customerRowMapper =
            (rs, rowNum) -> new Customer(rs.getInt("id"), rs.getString("name"));

    public CustomerService(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Collection<Customer> all() {
        return this.jdbcTemplate.query("select * from customers", this.customerRowMapper);
    }

    public Customer byName(final String name) {
        return this.jdbcTemplate.queryForObject("select * from customers where name =?", this.customerRowMapper, name);
    }
}
