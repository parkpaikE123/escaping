package com.ryu.escaping.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryu.escaping.community.domain.Community;

public interface CommunityRepository extends JpaRepository<Community,Integer> {

	
	public List<Community> findByUserId(int userId);
	
	public List<Community> findListBythemeIdOrderByIdDesc(int themeId);
	
	public List<Community> findTop3ListBythemeIdOrderByIdDesc(int themeId);
	
}
