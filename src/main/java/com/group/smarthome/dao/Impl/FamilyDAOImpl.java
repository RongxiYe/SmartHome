package com.group.smarthome.dao.Impl;

import com.group.smarthome.dao.FamilyDAO;
import com.group.smarthome.pojo.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FamilyDAOImpl implements FamilyDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Family queryFamilyInfo(String fid) {
        return null;
    }
}
