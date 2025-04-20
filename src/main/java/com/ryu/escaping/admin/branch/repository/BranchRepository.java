package com.ryu.escaping.admin.branch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryu.escaping.admin.branch.domain.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

	public List<Branch> findByLocation(String location);
}
