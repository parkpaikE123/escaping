package com.ryu.escaping.admin.theme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ryu.escaping.admin.theme.domain.Theme;

import jakarta.transaction.Transactional;


public interface ThemeRepository extends JpaRepository<Theme, Integer> {
	
	public int countByBranchId(int branchId);
	
	// SELECT * FROM `theme` WHERE `branchName` = #{}
	public Theme findByBranchName(String brnachName);
	
	@Query(value="SELECT * FROM `theme` WHERE `themeName` LIKE '%keyword%'",nativeQuery=true)
	public List<Theme> search(@Param("keyword") String keyword);
	
	public List<Theme> findByGenre(String genre);
	
	public List<Theme> findByBranchId(int branchId);
	
	// 지점 종속 삭제
	// SELECT * FROM `theme` WHERE `brachId` = #{}
	@Transactional
	public void deleteByBranchId(int branchId);
	
	
}
