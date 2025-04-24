package com.ryu.escaping.community;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.escaping.community.sevice.CommunityService;

@RestController
@RequestMapping("/community")
public class CommunityRestController {

	private final CommunityService communityService;
	public CommunityRestController(CommunityService communityService) {
		this.communityService = communityService;
	}
	
	// 커뮤니티 생성
	public Map<String, String> createCommunity(@RequestParam String userName
						,@RequestParam int themeId
						,@RequestParam String contents
						,@RequestParam int recruitCount
						,@RequestParam String state) {
		Map<String, String> resultMap = new HashMap<>();
		if(communityService.addCommunity(userName, themeId, contents, recruitCount, state)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
}
