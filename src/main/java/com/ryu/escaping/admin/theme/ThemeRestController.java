package com.ryu.escaping.admin.theme;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ryu.escaping.admin.branch.service.BranchService;
import com.ryu.escaping.admin.theme.domain.Theme;
import com.ryu.escaping.admin.theme.service.ThemeService;

@RestController
@RequestMapping("/admin")
public class ThemeRestController {

	private final ThemeService themeService;
	private final BranchService branchService;
	public ThemeRestController(ThemeService themeService, BranchService branchService) {
		this.themeService = themeService;
		this.branchService = branchService;
	}
	
	// 테마추가 API
	@PostMapping("/create-theme")
	public Map<String, String> addTheme(@RequestParam int branchId
										,@RequestParam String branchName
										,@RequestParam String themeName
										,@RequestParam int price
										,@RequestParam String themeStory
										,@RequestParam String genre
										,@RequestParam int runningTime
										,@RequestParam MultipartFile imageFile) {
		Map<String, String>resultMap = new HashMap<>();
		
		if(themeService.addTheme(branchId, branchName, themeName, price, genre, themeStory, runningTime, imageFile)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
	// 테마 검색 API
	public Map<String, String> themeSearch(@RequestParam String keyword) {
		Map<String, String> resultMap = new HashMap<>();
		List<Theme> themeList = themeService.getThemeListByName(keyword);
		
		if(themeList.isEmpty()) {
			resultMap.put("result", "fail");
		} else {
			resultMap.put("result", "success");
		}
		return resultMap;
	}
	
	// 테마삭제 API
	@DeleteMapping("/delete-theme")
	public Map<String, String> deleteTheme(@RequestParam int id) {
		Map<String, String>resultMap = new HashMap<>();
		
		if(themeService.deleteTheme(id)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
}
