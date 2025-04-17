package com.ryu.escaping.admin.theme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryu.escaping.admin.theme.domain.Theme;

import jakarta.transaction.Transactional;


public interface ThemeRepository extends JpaRepository<Theme, Integer> {
	
	public int countByBranchId(int branchId);
	
	// SELECT * FROM `theme` WHERE `branchName` = #{}
	public Theme findByBranchName(String brnachName);
	
	
	public List<Theme> findByBranchId(int branchId);
	
	// 지점 종속 삭제
	// SELECT * FROM `theme` WHERE `brachId` = #{}
	@Transactional
	public void deleteByBranchId(int branchId);
	
	
}
