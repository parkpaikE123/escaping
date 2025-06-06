package com.ryu.escaping.community;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.escaping.community.sevice.CommunityService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/community")
public class CommunityRestController {

	private final CommunityService communityService;
	public CommunityRestController(CommunityService communityService) {
		this.communityService = communityService;
	}
	
	// 커뮤니티 삭제
	@DeleteMapping("/delete")
	public Map<String, String> deleteCommunity(@RequestParam int id) {
		Map<String, String> resultMap = new HashMap<>();
		if(communityService.deleteCommunity(id)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
	// 커뮤니티 생성
	@PostMapping("/create")
	public Map<String, String> createCommunity(
						@RequestParam int themeId
						,@RequestParam String contents
						,@RequestParam int recruitCount
						,@RequestParam String state
						,HttpSession session) {
		Map<String, String> resultMap = new HashMap<>();
		String userName = (String)session.getAttribute("name");
		int userId = (Integer)session.getAttribute("userId");
		if(communityService.addCommunity(userId,userName, themeId, contents, recruitCount, state)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
}
