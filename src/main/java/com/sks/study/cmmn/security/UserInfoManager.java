package com.sks.study.cmmn.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import com.sks.study.cmmn.vo.UserInfoVO;

public class UserInfoManager extends JdbcDaoImpl {
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserDetails> userList = loadUsersByUsername(username);

		if (userList.size() == 0) {
			logger.debug("Query returned no results for user '" + username + "'");

			throw new UsernameNotFoundException(messages.getMessage(
					"JdbcDaoImpl.notFound", new Object[] { username },
					"Username {0} not found"));
		}

		UserInfoVO user = (UserInfoVO)userList.get(0); // contains no GrantedAuthority[]

		Set<GrantedAuthority> authoritySet = new HashSet<GrantedAuthority>();

		if (getEnableAuthorities()) {
			authoritySet.addAll(loadUserAuthorities(user.getUsername()));
		}

		if (getEnableGroups()) {
			authoritySet.addAll(loadGroupAuthorities(user.getUsername()));
		}

		List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(authoritySet);

		user.setAuthorities(dbAuths);

		if (dbAuths.size() == 0) {
			logger.debug("User '" + username
					+ "' has no authorities and will be treated as 'not found'");

			throw new UsernameNotFoundException(messages.getMessage(
					"JdbcDaoImpl.noAuthority", new Object[] { username },
					"User {0} has no GrantedAuthority"));
		}

//		return createUserDetails(username, user, dbAuths);
		return user;
	}
	
	@Override
	protected List<UserDetails> loadUsersByUsername(String username) {
		return getJdbcTemplate().query(getUsersByUsernameQuery(), new String[] { username },
				new RowMapper<UserDetails>() {
					public UserDetails mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String username = rs.getString(1);
						String password = rs.getString(2);
						String name = rs.getString(3);
						return new UserInfoVO(username, password, name, AuthorityUtils.NO_AUTHORITIES);
					}

				});
	}
	
	@Override
    protected List<GrantedAuthority> loadUserAuthorities(String username)
    {
        return getJdbcTemplate().query(getAuthoritiesByUsernameQuery(), new String[] {username}, new RowMapper<GrantedAuthority>() {
            public GrantedAuthority mapRow(ResultSet resultSet, int rowNum) throws SQLException
            {
                String roleName = getRolePrefix() + resultSet.getString(1);

                return new SimpleGrantedAuthority(roleName);
            }
        });
    }
	
	@Override
    protected List<GrantedAuthority> loadGroupAuthorities(String username)
    {
        return super.loadGroupAuthorities(username);
    }
}
