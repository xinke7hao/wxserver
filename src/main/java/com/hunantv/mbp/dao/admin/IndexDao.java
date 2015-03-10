package com.hunantv.mbp.dao.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.PagingDAO;
import com.hunantv.mbp.entity.admin.SystemLog;

@Service
public class IndexDao extends PagingDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 分页查询用户列表
	 * @Author：XuYanbo
	 * @Date：2014年12月7日
	 * @param user
	 * @param page
	 * @param loginUser
	 * @return
	 * @throws BmcException 
	 */
	public List<SystemLog> loadSystemLogs() throws BmcException {
		String sql = " select log_status, log_user, log_time, m.menu_name, log_type from tl_system_log, tm_menu m where module = m.menu_id and log_type != 'Q' order by id desc limit 0, " + BmcConstants.DESKTOP_LOG_SIZE;
		return jdbcTemplate.query(sql, new IndexLogMapper());
	}
	
	private class IndexLogMapper implements RowMapper<SystemLog> {

		@Override
		public SystemLog mapRow(ResultSet rs, int i) throws SQLException {
			SystemLog r = new SystemLog();
			r.setLogStatus(rs.getString("log_status"));
			r.setLogUser(rs.getString("log_user"));
			r.setLogTime(rs.getTimestamp("log_time"));
			r.setModuleName(rs.getString("menu_name"));
			r.setLogType(rs.getString("log_type"));
			return r;
		}
		
	}
	
}