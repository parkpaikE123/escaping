package com.ryu.escaping.proposal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ryu.escaping.proposal.domain.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal, Integer> {

	public List<Proposal> findByUserId(int userId);
	
	public List<Proposal> findByCommunityId(int communityId);
	
	@Query(value="UPDATE `proposal` SET `state` = state", nativeQuery = true)
	public Proposal updateProposalState(int id,String state);
}
