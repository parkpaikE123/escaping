package com.ryu.escaping.admin.theme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ryu.escaping.admin.theme.domain.Theme;


public interface ThemeRepository extends JpaRepository<Theme, Integer> {

	public int countByBranchId(@Param("branchId") int branchId);

	public Theme findAllByBranchName(@Param("branchName") String brnachName);
	
	
	public List<Theme> findByBranchId(@Param("branchId") int branchId);
	
	
}
