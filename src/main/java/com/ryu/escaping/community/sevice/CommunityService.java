package com.ryu.escaping.community.sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ryu.escaping.community.domain.Community;
import com.ryu.escaping.community.repository.CommunityRepository;
import com.ryu.escaping.proposal.domain.Proposal;
import com.ryu.escaping.proposal.repository.ProposalRepository;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
public class CommunityService {
	
	private final CommunityRepository communityRepository;
	private final ProposalRepository proposalRepository;
	public CommunityService(ProposalRepository proposalRepository, CommunityRepository communityRepository) {
		this.communityRepository = communityRepository;
		this.proposalRepository = proposalRepository;
	}

	public Community getCommunityEntity(int id) {
		Optional<Community>optionalCommunity = communityRepository.findById(id);
		return optionalCommunity.orElse(null);
	}
	
	public List<Community> getThreeCommunity(int themeId) {
		List<Community>communityList = communityRepository.findTop3ListBythemeIdOrderByIdDesc(themeId);
		return communityList;
	}
	
	public List<Community> getCommunityListByUserId(int userId) {
		List<Community> communityList = communityRepository.findByUserId(userId);
		return communityList;
	}
	
	public List<Community> getCommunity(int themeId) {
		List<Community> communityList = communityRepository.findListBythemeIdOrderByIdDesc(themeId);
		return communityList;
		
	}
	
	// 커뮤니티 삭세 (커뮤니티PK를 통한 제안서도 함께 삭제)
	@Transactional
	public boolean deleteCommunity(int id) {
		Optional<Community> optionalCommunity = communityRepository.findById(id);
		List<Proposal> proposalList = proposalRepository.findByCommunityId(id);
		
		if(optionalCommunity.isPresent()) {
			Community community = optionalCommunity.get();
			if(community.getId() != id) {
				return false;
			}
			try {
				for(Proposal proposal:proposalList) {
					proposalRepository.delete(proposal);
				}
				communityRepository.delete(community);
			} catch(PersistenceException e) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
	// 커뮤니티 생성
	public boolean addCommunity(int userId
								,String userName
								,int themeId
								,String contents
								,int recruitCount
								,String state) {
		Community community = Community.builder()
								.userId(userId)
								.userName(userName)
								.themeId(themeId)
								.contents(contents)
								.recruitCount(recruitCount)
								.state(state)
								.build();
		try {
			communityRepository.save(community);
		} catch (PersistenceException e) {
			return false;
		}
		return true;
	}
	
}
