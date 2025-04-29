package com.ryu.escaping.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ryu.escaping.admin.theme.domain.Theme;
import com.ryu.escaping.admin.theme.service.ThemeService;
import com.ryu.escaping.common.MD5HashingEncoder;
import com.ryu.escaping.community.domain.Community;
import com.ryu.escaping.community.sevice.CommunityService;
import com.ryu.escaping.proposal.domain.Proposal;
import com.ryu.escaping.proposal.service.ProposalService;
import com.ryu.escaping.user.domain.User;
import com.ryu.escaping.user.dto.ForMyProposal;
import com.ryu.escaping.user.repository.UserRepository;

import jakarta.persistence.PersistenceException;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final ThemeService themeService;
	private final CommunityService communityService;
	private final ProposalService proposalService;
	
	public UserService(ThemeService themeService
					, CommunityService communityService
					, ProposalService proposalService
					, UserRepository userRepository) {
		this.userRepository = userRepository;
		this.communityService = communityService;
		this.proposalService = proposalService;
		this.themeService = themeService;
	}
	
	// 내가작성한 제안서 dto
	public List<ForMyProposal> getMyProposal(int userId) {
		List<ForMyProposal> myProposalList = new ArrayList<>();
		List<Proposal> proposalList = proposalService.getProposalList(userId);
		for(Proposal proposal:proposalList) {
			
			Community community = communityService.getCommunityEntity(proposal.getCommunityId());
			
			Theme theme = themeService.getThemeById(community.getThemeId());
			
			ForMyProposal ForMyDto = ForMyProposal.builder()
									.imagePath(theme.getImagePath())
									.themeName(theme.getThemeName())
									.recruitCount(community.getRecruitCount())
									.state(proposal.getState())
									.createdAt(proposal.getCreatedAt())
									.build();
			myProposalList.add(ForMyDto);
		}
		return myProposalList;
	}
	
	// 로그인 서비스
	public User userLogin(String loginId, String password) {
		
		String encryptPassword = MD5HashingEncoder.encode(password);

		return userRepository.findByLoginIdAndPassword(loginId, encryptPassword);
	}
	
	// 아이디 중복체크 서비스
	public boolean isDuplicateId(String loginId) {
		
		int count = userRepository.countByLoginId(loginId);
		
		if(count == 0) {
			// 중복 안됨
			return false;
		} else {
			// 중복됨
			return true;
		}
		
	}
	
	// 회원가입 서비스
	public boolean addUser(String loginId
							, String password
							, String userName
							, String name
							, int age
							, String phoneNumber) {
		
		String encryptPassword = MD5HashingEncoder.encode(password);
		
		User user = User.builder()
				.loginId(loginId)
				.password(encryptPassword)
				.userName(userName)
				.name(name)
				.age(age)
				.phoneNumber(phoneNumber)
				.build();
		
		try {
			userRepository.save(user);
		} catch (PersistenceException e) {
			return false;
		}
		
		return true;
		
	}
}
