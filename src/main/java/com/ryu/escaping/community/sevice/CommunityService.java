package com.ryu.escaping.community.sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ryu.escaping.community.domain.Community;
import com.ryu.escaping.community.repository.CommunityRepository;

import jakarta.persistence.PersistenceException;

@Service
public class CommunityService {
	
	private final CommunityRepository communityRepository;
	public CommunityService(CommunityRepository communityRepository) {
		this.communityRepository = communityRepository;
	}

	public Community getCommunityEntity(int id) {
		Optional<Community>optionalCommunity = communityRepository.findById(id);
		return optionalCommunity.orElse(null);
	}
	
	public List<Community> getThreeCommunity(int themeId) {
		List<Community>communityList = communityRepository.findTop3ListBythemeIdOrderByIdDesc(themeId);
		return communityList;
	}
	
	public List<Community> getCommunity(int themeId) {
		List<Community> communityList = communityRepository.findListBythemeIdOrderByIdDesc(themeId);
		return communityList;
		
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
