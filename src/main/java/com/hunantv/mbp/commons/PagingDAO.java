package com.hunantv.mbp.commons;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * JDBC分页DAO
 * @author XuYanbo
 */
public abstract class PagingDAO {
	
	/**
	 * JDBC分页处理
	 * @param jdbcTemplate
	 * @param page
	 * @param baseQuerySql
	 * @param orderBySql
	 * @param args
	 * @param rowMapper
	 * @return
	 * @throws BmcException
	 */
	public <T> PageInfo<T> queryPage(JdbcTemplate jdbcTemplate, PageInfo<T> page, String baseQuerySql, String orderBySql, Object[] args, RowMapper<T> rowMapper) throws BmcException {

		String sql = baseQuerySql + " " + orderBySql;
		try {
			long totalCount = jdbcTemplate.queryForObject("select count(0) from ( " + baseQuerySql + " ) as a", args, Long.class);
			page.setTotal(totalCount);

			StringBuffer pagingSelect = new StringBuffer(100);
			pagingSelect.append(sql);
			pagingSelect.append(" limit " + page.getStartRow() + "," + page.getPageSize());

			List<T> records = new ArrayList<T>();
			records = jdbcTemplate.query(pagingSelect.toString(), args, rowMapper);
			page.setList(records);

		} catch (Exception e) {
			throw new BmcException("Exception in retrieving target list size:" + e.getMessage() + " - Sql: "
					+ baseQuerySql, e);
		}

		return page;
	}

}
