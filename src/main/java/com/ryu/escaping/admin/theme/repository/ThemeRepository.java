package com.ryu.escaping.admin.theme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ryu.escaping.admin.theme.domain.Theme;


public interface ThemeRepository extends JpaRepository<Theme, Integer> {

	public int countByBranchName(@Param("branchId") String branchName);
	
}
