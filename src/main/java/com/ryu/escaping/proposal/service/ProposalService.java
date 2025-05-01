package com.ryu.escaping.proposal.service;

import java.util.List;
import java.util.Optional;

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
	
	// 상태 업데이트
	public boolean updateProposal(int id, String state) {
		Optional<Proposal> optionalProposal = proposalRepository.findById(id);
		if(optionalProposal.isPresent()) {
			Proposal proposal = optionalProposal.get();
			if(proposal.getId() != id) {
				return false;
			}
			try {
				Proposal updateProposal = proposalRepository.updateProposalState(id, state);
				proposalRepository.save(updateProposal);
			} catch(PersistenceException e) {
				return false;
			}
		} else{
			return false;
		}
		return true;
	}
	
	// 삭제
	public boolean deleteProposal(int id) {
		Optional<Proposal> optionalProposal = proposalRepository.findById(id);
		if(optionalProposal.isPresent()) {
			Proposal proposal = optionalProposal.get();
			if(proposal.getId() != id) {
				return false;
			}
			try {
				proposalRepository.delete(proposal);
			} catch(PersistenceException e) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
	
	public List<Proposal> getProposalListByCommunityId(int communityId) {
		return proposalRepository.findByCommunityId(communityId);
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
