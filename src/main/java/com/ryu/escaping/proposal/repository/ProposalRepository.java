package com.ryu.escaping.proposal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryu.escaping.proposal.domain.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal, Integer> {

	public List<Proposal> findByUserId(int userId);
	
	public List<Proposal> findByCommunityId(int communityId);
}
