package com.ryu.escaping.admin.theme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ryu.escaping.admin.theme.domain.Theme;


public interface ThemeRepository extends JpaRepository<Theme, Integer> {

	public int countByBranchId(int branchId);
	
	public Theme findAllByBranchName(String brnachName);
	
	public List<Theme> findByBranchId(int branchId);

	// 지점 종속 삭제
	// SELECT * FROM `theme` WHERE `brachId` = #{}
	public void deleteByBranchId(Theme theme);
	
	public List<Theme> findAllByBranchId(int branchId);
	
}
