package com.ryu.escaping.admin.branch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ryu.escaping.admin.branch.domain.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

	
	
	
}
