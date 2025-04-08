package com.ryu.escaping.admin.theme;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ryu.escaping.admin.theme.service.ThemeService;

@RestController
@RequestMapping("/admin")
public class ThemeRestController {

	private final ThemeService themeService;
	public ThemeRestController(ThemeService themeService) {
		this.themeService = themeService;
	}
	
	// 테마추가 API
	@PostMapping("/create-theme")
	public Map<String, String> addTheme(
										@RequestParam String branchName
										,@RequestParam String themeName
										,@RequestParam int price
										,@RequestParam String genre
										,@RequestParam int ruunningTime
										,@RequestParam MultipartFile imageFile) {
		Map<String, String>resultMap = new HashMap<>();
		
		if(themeService.addTheme(branchName, themeName, price, genre, ruunningTime, imageFile)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
	
}
