package com.ryu.escaping.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.ryu.escaping.user.dto.ForReceiveProposal;
import com.ryu.escaping.user.dto.ForReceiveProposalDetail;
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
	
	// 내가 받은 제안서 detail dto
	public List<ForReceiveProposalDetail> getProposalDetail(int communityId) {
		List<ForReceiveProposalDetail> receiveList = new ArrayList<>();
		List<Proposal> proposalList = proposalService.getProposalListByCommunityId(communityId);
		Community community = communityService.getCommunityEntity(communityId);
		Theme theme = themeService.getThemeById(community.getThemeId());
		for(Proposal proposal:proposalList) {
			Optional<User>optionalUser = userRepository.findById(proposal.getUserId());
			
			if(optionalUser.isEmpty()) {
				return null;
			}
			User user = optionalUser.get();
			
			ForReceiveProposalDetail forDetail = ForReceiveProposalDetail.builder()
												.proposalId(proposal.getId())
												.imagePath(theme.getImagePath())
												.themeName(theme.getThemeName())
												.proposalUserName(user.getUserName())
												.contents(proposal.getContents())
												.build();
			receiveList.add(forDetail);
		}
		return receiveList;
		
	}
	
	// 내가 받은 제안서 화면 dto
	public List<ForReceiveProposal> getReceiveProposal(int userId) {
		List<ForReceiveProposal> receiveProposalList = new ArrayList<>();
		List<Community> communityList = communityService.getCommunityListByUserId(userId);
		
		for(Community community:communityList) {
			// 받은 제안서 카운트
			int sendCount = 0;
			
			List<Proposal> proposalList = proposalService.getProposalListByCommunityId(community.getId());
			for(Proposal proposal:proposalList) {
				if(proposal.getCommunityId() == community.getId()) {
					sendCount ++;
				}
			}
			
			Theme theme = themeService.getThemeById(community.getThemeId());
			
			ForReceiveProposal forReceive = ForReceiveProposal.builder()
											.imagePath(theme.getImagePath())
											.communityId(community.getId())
											.themeName(theme.getThemeName())
											.sendCount(sendCount)
											.recruitCount(community.getRecruitCount())
											.state(community.getState())
											.createdAt("예약테이블 생성일")
											.build();
			receiveProposalList.add(forReceive);
		}
		return receiveProposalList;
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
