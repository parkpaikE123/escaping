package com.ryu.escaping.community.sevice;

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

	// 커뮤니티 생성
	public boolean addCommunity(String userName
								,int themeId
								,String contents
								,int recruitCount
								,String state) {
		Community community = Community.builder()
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
