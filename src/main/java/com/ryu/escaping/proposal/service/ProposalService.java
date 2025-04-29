package com.ryu.escaping.proposal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryu.escaping.proposal.domain.Proposal;
import com.ryu.escaping.proposal.repository.ProposalRepository;

import jakarta.persistence.PersistenceException;

@Service
public class ProposalService {

	private final ProposalRepository proposalRepository;

	
	public ProposalService(ProposalRepository proposalRepository) {
		this.proposalRepository = proposalRepository;
	}
	
	public List<Proposal> getProposalList(int userId) {
		List<Proposal> proposalList = proposalRepository.findByUserId(userId);
		return proposalList;
	}
	
	
	public boolean addProposal(int userId
							,int communityId
							,String contents
							,String state) {
		
		Proposal proposal = Proposal.builder()
		.userId(userId)
		.communityId(communityId)
		.contents(contents)
		.state(state)
		.build();
		
		try {
			proposalRepository.save(proposal);
		} catch(PersistenceException e) {
			return false;
		}
		return true;
	}
	
}
