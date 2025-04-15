package com.ryu.escaping.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ryu.escaping.user.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public int countByLoginId(@Param("loginId") String loginId);
	
	public User findByLoginIdAndPassword(@Param("loginId") String loginId,@Param("password") String password);
	
}
