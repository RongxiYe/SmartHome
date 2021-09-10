package com.group.smarthome.dao.Impl;

import com.group.smarthome.dao.FamilyDAO;
import com.group.smarthome.pojo.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class FamilyDAOImpl implements FamilyDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Family queryFamilyInfo(String fid) {
        String sql = "select * from family where familyID=?";
        Family family;
        try{
            family = jdbcTemplate.queryForObject(sql,new RowMapper<Family>() {

                @Override
                public Family mapRow(ResultSet rs, int i) throws SQLException {
                    Family family = new Family();
                    family.setFamilyID(rs.getString("familyID"));
                    family.setAddress(rs.getString("address"));
                    family.setPostcode(rs.getString("postcode"));
                    return family;
                }
            },fid);
        }catch (org.springframework.dao.EmptyResultDataAccessException e) {
            family = null;
        }
        return null;
    }
}
