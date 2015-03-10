package com.xuyanbo.wx.dao.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;
import org.springframework.stereotype.Service;

import com.xuyanbo.wx.command.LogCommand;
import com.xuyanbo.wx.command.LoginCommand;
import com.xuyanbo.wx.command.RightCommand;
import com.xuyanbo.wx.commons.ApplicationConfigs;
import com.xuyanbo.wx.commons.BmcConstants;
import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.commons.PageInfo;
import com.xuyanbo.wx.commons.PagingDAO;
import com.xuyanbo.wx.dto.MenuDesc;
import com.xuyanbo.wx.dto.RightVo;
import com.xuyanbo.wx.entity.admin.Department;
import com.xuyanbo.wx.entity.admin.Group;
import com.xuyanbo.wx.entity.admin.GroupData;
import com.xuyanbo.wx.entity.admin.LoginLog;
import com.xuyanbo.wx.entity.admin.Menu;
import com.xuyanbo.wx.entity.admin.Role;
import com.xuyanbo.wx.entity.admin.SystemData;
import com.xuyanbo.wx.entity.admin.SystemLog;
import com.xuyanbo.wx.entity.admin.User;

@Service
public class SystemModuleDao extends PagingDAO {

	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(SystemModuleDao.class);
	
	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 获取菜单描述说明
	 * @Author：XuYanbo
	 * @Date：2015年2月10日
	 * @return
	 */
	@PostConstruct
	public void initMenuDescMap() throws BmcException {
		logger.debug("**** Initial Menu Description Map ****");
		String sql = " select menu_id, menu_desc from tm_menu where menu_desc is not null ";
		List<MenuDesc> res = jdbcTemplate.query(sql, new MenuDescRowMapper());
		res.forEach(x->{ApplicationConfigs.menuDescMap.put(x.getMenuId(), x.getMenuDesc());});
	}
	
	private class MenuDescRowMapper implements RowMapper<MenuDesc> {

		@Override
		public MenuDesc mapRow(ResultSet rs, int i) throws SQLException {
			return new MenuDesc(rs.getInt("menu_id"), rs.getString("menu_desc"));
		}
		
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
	public PageInfo<User> searchUsers(User user, PageInfo<User> page, User loginUser) throws BmcException {
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		StringBuilder orderSql = new StringBuilder();
		sql.append(" select u.user_id, u.user_code, u.user_name, u.phone, d.depart_id, d.depart_name, u.email, u.user_status, u.is_admin, u.create_time, u.update_time");
		sql.append(" from tm_user u, tm_department d where u.depart_id = d.depart_id ");
		
		if(user!=null){
			if(user.getUserCode()!=null && !"".equals(user.getUserCode())){
				args.add("%"+user.getUserCode()+"%");
				sql.append(" and u.user_code like ? ");
			}
			if(user.getUserName()!=null && !"".equals(user.getUserName())){
				args.add("%"+user.getUserName()+"%");
				sql.append(" and u.user_name like ? ");
			}
			if(user.getUserStatus()!=null && !"".equals(user.getUserStatus())){
				args.add(user.getUserStatus());
				sql.append(" and u.user_status = ? ");
			}
		}
		
		//过滤数据权限
		if(null != loginUser){
			if(BmcConstants.NO.equals(loginUser.getIsAdmin())){
				sql.append(" and d.depart_id in (");
				sql.append(" select r.data_id from tr_group_data r, tm_group g, tr_user_group ug");
				sql.append(" where r.group_id = g.group_id and g.group_id = ug.group_id and r.data_type = '" + BmcConstants.SYSTEM_DATA_TYPE_DEPARTMENT + "'");
				sql.append(" and ug.user_id = " + loginUser.getUserId() + " ) ");
			}
		}
		
		if(page.isOrderSetted()){
			orderSql.append(" order by " + page.getOrderBy() + " " + page.getOrder());
		} else {
			orderSql.append(" order by u.update_time desc ");
		}
		
		return queryPage(jdbcTemplate, page, sql.toString(), orderSql.toString(), args.toArray(), new UserRowMapper());
	}
	
	private class UserRowMapper implements RowMapper<User> {

		//user_id, user_code, user_name, age, department, email, user_status, create_time, update_time
		
		@Override
		public User mapRow(ResultSet rs, int i) throws SQLException {
			User u = new User();
			u.setUserId(rs.getInt("user_id"));
			u.setUserCode(rs.getString("user_code"));
			u.setUserName(rs.getString("user_name"));
			u.setPhone(rs.getString("phone"));
			u.setDepartId(rs.getInt("depart_id"));
			u.setDepartName(rs.getString("depart_name"));
			u.setEmail(rs.getString("email"));
			u.setUserStatus(rs.getString("user_status"));
			u.setIsAdmin(rs.getString("is_admin"));
			u.setCreateTime(rs.getTimestamp("create_time"));
			u.setUpdateTime(rs.getTimestamp("update_time"));
			return u;
		}
		
	}
	
	/**
	 * 分页查询角色列表
	 * @Author：XuYanbo
	 * @Date：2014年12月7日
	 * @param role
	 * @param page
	 * @return
	 * @throws BmcException 
	 */
	public PageInfo<Role> searchRoles(Role role, PageInfo<Role> page) throws BmcException {
		
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		StringBuilder orderSql = new StringBuilder();
		
		sql.append(" select role_id, role_name, role_status, create_time, update_time from tm_role where 1=1 ");
		
		if(role!=null){
			if(role.getRoleName()!=null && !"".equals(role.getRoleName())){
				args.add("%"+role.getRoleName()+"%");
				sql.append(" and role_name like ? ");
			}
			if(role.getRoleStatus()!=null && !"".equals(role.getRoleStatus())){
				args.add(role.getRoleStatus());
				sql.append(" and role_status = ? ");
			}
		}
		
		if(page.isOrderSetted()){
			orderSql.append(" order by " + page.getOrderBy() + " " + page.getOrder());
		} else {
			orderSql.append(" order by update_time desc ");
		}
		
		return queryPage(jdbcTemplate, page, sql.toString(), orderSql.toString(), args.toArray(), new RoleRowMapper());
	}
	
	private class RoleRowMapper implements RowMapper<Role> {

		//role_id, role_name, role_status, create_time, update_time
		
		@Override
		public Role mapRow(ResultSet rs, int i) throws SQLException {
			Role r = new Role();
			r.setRoleId(rs.getInt("role_id"));
			r.setRoleName(rs.getString("role_name"));
			r.setRoleStatus(rs.getString("role_status"));
			r.setCreateTime(rs.getTimestamp("create_time"));
			r.setUpdateTime(rs.getTimestamp("update_time"));
			return r;
		}
		
	}
	
	/**
	 * 分页查询菜单列表
	 * @Author：XuYanbo
	 * @Date：2014年12月7日
	 * @param right
	 * @param page
	 * @return
	 * @throws BmcException 
	 */
	public PageInfo<Menu> searchMenus(RightCommand menuCmd, PageInfo<Menu> page) throws BmcException {
		
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		StringBuilder orderSql = new StringBuilder();
		
		sql.append(" select n.menu_id, m.module_id, m.module_name, n.menu_pid, n.menu_name, n.menu_status, n.menu_desc ");
		sql.append(" from tm_module m, tm_menu n where m.module_id = n.module_id ");
		
		if(menuCmd!=null && menuCmd.getModuleId()!=null){
			sql.append(" and m.module_id = ? ");
			args.add(menuCmd.getModuleId());
		}
		
		orderSql.append(" order by m.module_index, n.menu_index ");
		
		return queryPage(jdbcTemplate, page, sql.toString(), orderSql.toString(), args.toArray(), new MenuRowMapper());
	}
	
	private class MenuRowMapper implements RowMapper<Menu> {

		//n.menu_id, m.module_id, n.menu_pid, n.menu_name, n.menu_status, n.menu_desc, n.update_time
		
		@Override
		public Menu mapRow(ResultSet rs, int i) throws SQLException {
			Menu m = new Menu();
			m.setMenuId(rs.getInt("menu_id"));
			m.setModuleId(rs.getInt("module_id"));
			m.setModuleName(rs.getString("module_name"));
			m.setMenuPid(rs.getInt("menu_pid"));
			m.setMenuName(rs.getString("menu_name"));
			m.setMenuStatus(rs.getString("menu_status"));
			m.setMenuDesc(rs.getString("menu_desc"));
			return m;
		}
		
	}
	
	/**
	 * 分页查询功能列表
	 * @Author：XuYanbo
	 * @Date：2014年12月7日
	 * @param right
	 * @param page
	 * @return
	 * @throws BmcException 
	 */
	public PageInfo<RightVo> searchRights(RightCommand rightCmd, PageInfo<RightVo> page) throws BmcException {
		
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		StringBuilder orderSql = new StringBuilder();
		
		sql.append(" select m.module_id, m.module_name, m.module_status, n.menu_id, n.menu_name, n.menu_code, n.menu_url, n.menu_status,");
		sql.append(" r.right_id, r.right_name, r.right_method, r.right_status, r.create_time, r.update_time");
		sql.append(" from tm_module m, tm_menu n, tm_right r");
		sql.append(" where m.module_id = r.module_id and n.menu_id = r.menu_id ");
		
		if(rightCmd!=null){
			if(rightCmd.getModuleId()!=null){
				args.add(rightCmd.getModuleId());
				sql.append(" and m.module_id = ? ");
			}
			if(rightCmd.getMenuId()!=null){
				args.add(rightCmd.getMenuId());
				sql.append(" and n.menu_id = ? ");
			}
		}
		
		if(page.isOrderSetted()){
			orderSql.append(" order by " + page.getOrderBy() + " " + page.getOrder());
		} else {
			orderSql.append(" order by r.update_time desc, m.module_id, n.menu_id, r.right_id ");
		}
		
		return queryPage(jdbcTemplate, page, sql.toString(), orderSql.toString(), args.toArray(), new RightRowMapper());
	}
	
	private class RightRowMapper implements RowMapper<RightVo> {

		//m.module_id, m.module_name, m.module_status, n.menu_id, n.menu_name, n.menu_code, n.menu_url, n.menu_status
		//r.right_id, r.right_name, r.right_method, r.right_status, r.create_time, r.update_time
		
		@Override
		public RightVo mapRow(ResultSet rs, int i) throws SQLException {
			RightVo r = new RightVo();
			r.setModuleId(rs.getInt("module_id"));
			r.setModuleName(rs.getString("module_name"));
			r.setModuleStatus(rs.getString("module_status"));
			r.setMenuId(rs.getInt("menu_id"));
			r.setMenuName(rs.getString("menu_name"));
			r.setMenuCode(rs.getString("menu_code"));
			r.setMenuUrl(rs.getString("menu_url"));
			r.setMenuStatus(rs.getString("menu_status"));
			r.setRightId(rs.getInt("right_id"));
			r.setRightName(rs.getString("right_name"));
			r.setRightMethod(rs.getString("right_method"));
			r.setRightStatus(rs.getString("right_status"));
			r.setCreateTime(rs.getTimestamp("create_time"));
			r.setUpdateTime(rs.getTimestamp("update_time"));
			return r;
		}
		
	}
	
	/**
	 * 分页查询日志列表
	 * @Author：XuYanbo
	 * @Date：2014年12月7日
	 * @param log
	 * @param page
	 * @return
	 * @throws BmcException 
	 */
	public PageInfo<SystemLog> searchSystemLogs(LogCommand log, PageInfo<SystemLog> page) throws BmcException {
		
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		StringBuilder orderSql = new StringBuilder();
		
		sql.append(" select log.id, m.menu_id, m.menu_name, log.log_style, log.log_status, log.log_type, log.log_time, log.log_user, log.log_ip, log.log_desc from tl_system_log log, tm_menu m where log.module = m.menu_id ");
		
		if(log!=null){
			if(log.getModule() != null){
				args.add(log.getModule());
				sql.append(" and m.menu_id = ?");
			}
			if(StringUtils.isNotBlank(log.getStyle())){
				args.add(log.getStyle());
				sql.append(" and log.log_style = ?");
			}
			if(StringUtils.isNotBlank(log.getStatus())){
				args.add(log.getStatus());
				sql.append(" and log.log_status = ?");
			}
			if(StringUtils.isNotBlank(log.getType())){
				args.add(log.getType());
				sql.append(" and log.log_type = ?");
			}
			if(StringUtils.isNotBlank(log.getStartDay())){
				sql.append(" and log.log_time >= '"+log.getStartDay()+" 00:00:00'");
			}
			if(StringUtils.isNotBlank(log.getEndDay())){
				sql.append(" and log.log_time <= '"+log.getEndDay()+" 23:59:59'");
			}
		}
		
		if(page.isOrderSetted()){
			orderSql.append(" order by " + page.getOrderBy() + " " + page.getOrder());
		} else {
			orderSql.append(" order by log.log_time desc ");
		}
		
		return queryPage(jdbcTemplate, page, sql.toString(), orderSql.toString(), args.toArray(), new SystemLogRowMapper());
	}
	
	/**
	 * 根据查询条件删除日志
	 * 删除操作属于敏感记录，不予清除
	 * @Author：XuYanbo
	 * @Date：2014年11月17日
	 * @param log
	 */
	public void deleteSystemLogs(LogCommand log) {
		List<Object> args = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("delete from tl_system_log where log_type != '" + BmcConstants.LOG_TYPE_DELETE + "' ");
		
		if(log!=null){
			if(log.getModule() != null){
				args.add(log.getModule());
				sql.append(" and module = ?");
			}
			if(StringUtils.isNotBlank(log.getStyle())){
				args.add(log.getStyle());
				sql.append(" and log_style = ?");
			}
			if(StringUtils.isNotBlank(log.getStatus())){
				args.add(log.getStatus());
				sql.append(" and log_status = ?");
			}
			if(StringUtils.isNotBlank(log.getType())){
				args.add(log.getType());
				sql.append(" and log_type = ?");
			}
			if(StringUtils.isNotBlank(log.getStartDay())){
				sql.append(" and log_time >= '"+log.getStartDay()+" 00:00:00'");
			}
			if(StringUtils.isNotBlank(log.getEndDay())){
				sql.append(" and log_time <= '"+log.getEndDay()+" 23:59:59'");
			}
		}
		jdbcTemplate.update(sql.toString(), args.toArray());
	}
	
	private class SystemLogRowMapper implements RowMapper<SystemLog> {

		//id, module, log_style, log_status, log_type, log_time, log_user, log_ip, log_desc
		
		@Override
		public SystemLog mapRow(ResultSet rs, int i) throws SQLException {
			SystemLog log = new SystemLog();
			log.setId(rs.getInt("id"));
			log.setModule(rs.getInt("menu_id"));
			log.setModuleName(rs.getString("menu_name"));
			log.setLogStyle(rs.getString("log_style"));
			log.setLogStatus(rs.getString("log_status"));
			log.setLogType(rs.getString("log_type"));
			log.setLogTime(rs.getTimestamp("log_time"));
			log.setLogUser(rs.getString("log_user"));
			log.setLogIp(rs.getString("log_ip"));
			log.setLogDesc(rs.getString("log_desc"));
			return log;
		}
		
	}

	/**
	 * 数据角色分页查询
	 * @Author：XuYanbo
	 * @Date：2014年12月9日
	 * @param group
	 * @param page
	 * @return
	 * @throws BmcException 
	 */
	public PageInfo<Group> searchGroups(Group group, PageInfo<Group> page) throws BmcException {
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		StringBuilder orderSql = new StringBuilder();
		
		sql.append(" select group_id, group_name, group_status, create_time, update_time from tm_group where 1=1 ");
		
		if(group!=null){
			if(group.getGroupName()!=null && !"".equals(group.getGroupName())){
				args.add("%"+group.getGroupName()+"%");
				sql.append(" and group_name like ? ");
			}
			if(group.getGroupStatus()!=null && !"".equals(group.getGroupStatus())){
				args.add(group.getGroupStatus());
				sql.append(" and group_status = ? ");
			}
		}
		
		if(page.isOrderSetted()){
			orderSql.append(" order by " + page.getOrderBy() + " " + page.getOrder());
		} else {
			orderSql.append(" order by update_time desc ");
		}
		
		return queryPage(jdbcTemplate, page, sql.toString(), orderSql.toString(), args.toArray(), new GroupRowMapper());
	}
	
	private class GroupRowMapper implements RowMapper<Group> {

		//group_id, role_name, role_status, create_time, update_time
		
		@Override
		public Group mapRow(ResultSet rs, int i) throws SQLException {
			Group g = new Group();
			g.setGroupId(rs.getInt("group_id"));
			g.setGroupName(rs.getString("group_name"));
			g.setGroupStatus(rs.getString("group_status"));
			g.setCreateTime(rs.getTimestamp("create_time"));
			g.setUpdateTime(rs.getTimestamp("update_time"));
			return g;
		}
		
	}

	/**
	 * 设置数据角色关联数据
	 * @Author：XuYanbo
	 * @Date：2014年12月10日
	 * @param groupId
	 */
	public void insertGroupData(final Integer groupId, final List<GroupData> datas){
		String sql = "insert into tr_group_data(group_id, data_type, data_id) values(?, ?, ?)";
		
		BatchSqlUpdate batchInsert = new BatchSqlUpdate(jdbcTemplate.getDataSource(), sql);
		batchInsert.setBatchSize(100);
		batchInsert.declareParameter(new SqlParameter("group_id", Types.INTEGER));
		batchInsert.declareParameter(new SqlParameter("data_type", Types.VARCHAR));
		batchInsert.declareParameter(new SqlParameter("data_id", Types.VARCHAR));
		
		Object[] values = new Object[3];
		datas.forEach(data -> {
			values[0] = groupId;
			values[1] = data.getDataType();
			values[2] = data.getDataId();
			batchInsert.update(values);
		});
		
		batchInsert.flush();
	}

	/**
	 * 搜索部门
	 * @Author：XuYanbo
	 * @Date：2014年12月12日
	 * @param depart
	 * @param page
	 * @param loginUser
	 * @return
	 * @throws BmcException 
	 */
	public PageInfo<Department> searchDepartments(Department depart, PageInfo<Department> page, User loginUser) throws BmcException {
		
		StringBuffer sql = new StringBuffer();
		StringBuffer orderSql = new StringBuffer();
		List<Object> args = new ArrayList<Object>();
		sql.append(" select depart_id, parent_id, depart_code, depart_name, create_time, update_time from tm_department where 1=1");
		
		if(depart!=null){
			if(StringUtils.isNotBlank(depart.getDepartCode())){
				args.add("%"+depart.getDepartCode()+"%");
				sql.append(" and depart_code like ? ");
			}
			if(StringUtils.isNotBlank(depart.getDepartName())){
				args.add("%"+depart.getDepartName()+"%");
				sql.append(" and depart_name like ? ");
			}
		}
		
		//过滤数据权限
		if(null != loginUser){
			if(BmcConstants.NO.equals(loginUser.getIsAdmin())){
				sql.append(" and depart_id in (");
				sql.append(" select r.data_id from tr_group_data r, tm_group g, tr_user_group ug");
				sql.append(" where r.group_id = g.group_id and g.group_id = ug.group_id and r.data_type = '" + BmcConstants.SYSTEM_DATA_TYPE_DEPARTMENT + "'");
				sql.append(" and ug.user_id = " + loginUser.getUserId() + " ) ");
			}
		}
		
		if(page.isOrderSetted()){
			orderSql.append(" order by " + page.getOrderBy() + " " + page.getOrder());
		} else {
			orderSql.append(" order by update_time desc  ");
		}
		
		return queryPage(jdbcTemplate, page, sql.toString(), orderSql.toString(), args.toArray(), new DepartmentRowMapper());
	}
	
	private class DepartmentRowMapper implements RowMapper<Department> {

		@Override
		public Department mapRow(ResultSet rs, int i) throws SQLException {
			Department d = new Department();
			d.setDepartId(rs.getInt("depart_id"));
			d.setParentId(rs.getInt("parent_id"));
			d.setDepartCode(rs.getString("depart_code"));
			d.setDepartName(rs.getString("depart_name"));
			d.setCreateTime(rs.getTimestamp("create_time"));
			d.setUpdateTime(rs.getTimestamp("update_time"));
			return d;
		}
		
	}
	
	/**
	 * 获取数据列表
	 * @Author：XuYanbo
	 * @Date：2015年1月15日
	 * @param dataType
	 * @return
	 */
	public List<SystemData> getSystemDatas(String dataType) throws BmcException {
		
		String sql = " select data_id, data_code, data_name from tm_system_data where data_type = '" + dataType + "' order by data_id";
		return jdbcTemplate.query(sql.toString(), new SystemDataRowMapper());
	}
	
	/**
	 * 获取用户可见数据列表
	 * @Author：XuYanbo
	 * @Date：2014年12月24日
	 * @param user
	 * @param dataType
	 * @param dataIds
	 * @return
	 */
	public List<SystemData> getSystemDatas(User loginUser, String dataType, String dataIds) throws BmcException {
		
		StringBuffer sql = new StringBuffer(" select data_id, data_code, data_name from tm_system_data where data_status = 'Y' and data_type = '" + dataType + "'");
		
		if(null!=loginUser && BmcConstants.NO.equals(loginUser.getIsAdmin())){
			sql.append(" and data_id in (" + dataIds + " ) ");
		}
		
		sql.append(" order by data_id");
		
		return jdbcTemplate.query(sql.toString(), new SystemDataRowMapper());
	}
	
	private class SystemDataRowMapper implements RowMapper<SystemData> {

		@Override
		public SystemData mapRow(ResultSet rs, int i) throws SQLException {
			SystemData d = new SystemData();
			d.setDataId(rs.getInt("data_id"));
			d.setDataCode(rs.getString("data_code"));
			d.setDataName(rs.getString("data_name"));
			return d;
		}
		
	}
	
	/**
	 * 分页查询用户登录日志列表
	 * @Author：XuYanbo
	 * @Date：2015年3月4日
	 * @param log
	 * @param page
	 * @return
	 * @throws BmcException 
	 */
	public PageInfo<LoginLog> searchLoginLogs(LoginCommand log, PageInfo<LoginLog> page) throws BmcException {
		
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		StringBuilder orderSql = new StringBuilder();
		
		sql.append(" select id, login_user, login_time, login_ip, login_status, login_desc from tl_login_log where 1=1 ");
		
		if(log!=null){
			if(StringUtils.isNotBlank(log.getUsercode())){
				args.add(log.getUsercode());
				sql.append(" and login_user = ?");
			}
			if(StringUtils.isNotBlank(log.getStatus())){
				args.add(log.getStatus());
				sql.append(" and login_status = ?");
			}
			if(StringUtils.isNotBlank(log.getStartDay())){
				sql.append(" and login_time >= '"+log.getStartDay()+":00'");
			}
			if(StringUtils.isNotBlank(log.getEndDay())){
				sql.append(" and login_time <= '"+log.getEndDay()+":59'");
			}
		}
		
		orderSql.append(" order by login_time desc ");
		
		return queryPage(jdbcTemplate, page, sql.toString(), orderSql.toString(), args.toArray(), new LoginLogRowMapper());
	}
	
	private class LoginLogRowMapper implements RowMapper<LoginLog> {

		@Override
		public LoginLog mapRow(ResultSet rs, int i) throws SQLException {
			LoginLog log = new LoginLog();
			log.setId(rs.getInt("id"));
			log.setLoginUser(rs.getString("login_user"));
			log.setLoginTime(rs.getTimestamp("login_time"));
			log.setLoginStatus(rs.getString("login_status"));
			log.setLoginIp(rs.getString("login_ip"));
			log.setLoginDesc(rs.getString("login_desc"));
			return log;
		}
		
	}
	
	/**
	 * 获取对应数据表自增字段的下一个值
	 * @Author：XuYanbo
	 * @Date：2014年12月17日
	 * @param tableName
	 * @return
	 */
	public int getNextKey(String tableName){
//		String sql = "SELECT Auto_increment FROM information_schema.`TABLES` WHERE Table_Schema='" + dbName + "' AND table_name = '" + tableName + "'";
		String sql = "SELECT Auto_increment FROM information_schema.`TABLES` WHERE table_name = '" + tableName + "'";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}