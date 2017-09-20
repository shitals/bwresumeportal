/**
 * 
 */
package com.bitwiseglobal.resumemgmt.eo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bitwiseglobal.resumemgmt.vo.User;

/**
 * @author Chetan Menge
 *
 */
@Repository
public class UsersEO {
	@Autowired
    private JdbcTemplate jdbcTemplate;
 
    @Transactional(readOnly=true)
    public List<User> findAll() {
    	String sql="select * from users";
       
    	List<User> users  = jdbcTemplate.query(sql,
    			new BeanPropertyRowMapper(User.class));
    	return users;
    }

}
