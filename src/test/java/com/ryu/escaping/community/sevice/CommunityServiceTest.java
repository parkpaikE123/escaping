package com.ryu.escaping.community.sevice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

@SpringBootTest
class CommunityServiceTest {

	@Autowired
	private CommunityService communityService;
	
	@Transactional
	@Test
	public void addCommunityTest() {
		// 준비
		int userId = 1;
		String userName ="123";
		int themeId = 2;
		String contents = "1234252";
		int recruitCount = 2;
		String state = "메롱";
		
		// 실행
		boolean success = communityService.addCommunity(userId,userName, themeId, contents, recruitCount, state);
		
		// 검증
		assertEquals(success,true);
		
	}
}
