package com.ryu.escaping.admin.theme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ryu.escaping.admin.branch.repository.BranchRepository;
import com.ryu.escaping.admin.theme.domain.Theme;
import com.ryu.escaping.admin.theme.repository.ThemeRepository;
import com.ryu.escaping.common.FileManager;

import jakarta.persistence.PersistenceException;


@Service
public class ThemeService {

	private final ThemeRepository themeRepository;
	private final BranchRepository branchRepository;
	public ThemeService(ThemeRepository themeRepository, BranchRepository branchRepository) {
		this.themeRepository = themeRepository;
		this.branchRepository = branchRepository;
	}
	
	public Theme getThemeById(int id) {
		Optional<Theme> optionalTheme = themeRepository.findById(id);
			
		return optionalTheme.orElse(null);
	}
	
	public List<Theme> getThemeListBySearch(String keyword) {
		List<Theme>themeList = themeRepository.findByGenre(keyword);
		return themeList;
	}
	
	public List<Theme> getThemeList() {
		List<Theme> themeList = themeRepository.findAll();
		return themeList;
	}
	
	
	public int countTheme(int branchId) {
		return themeRepository.countByBranchId(branchId);
	}
	
	public List<Theme> getThemeByBranchId(int branchId) {
		List<Theme> themeList = themeRepository.findByBranchId(branchId);
		if(themeList == null || themeList.isEmpty()) {
			return null;
		}
		return themeList;
	}
	
	public boolean deleteTheme(int id) {
		
		Optional<Theme> optionalTheme = themeRepository.findById(id);
		
		if(optionalTheme.isPresent()) {
			Theme theme = optionalTheme.get();
			
			// 삭제 실패
			if(theme.getId() != id) {
				return false;
			}
			
			// 테마 사진파일 삭제
			FileManager.removeThemeFile(theme.getImagePath());
						
			try {
			themeRepository.delete(theme);
			} catch(PersistenceException e) {
				return false;
			}
			
		} else {
			return false;
		}
		return true;
	}
	
	
	public boolean addTheme(int branchId
							,String branchName
							,String themeName
							,int price
							,String genre
							,String themeStory
							,int runningTime
							,MultipartFile imagefile) {
		
		String themeImagePath = FileManager.saveTheme(imagefile);
		
		Theme theme = Theme.builder()
				.branchId(branchId)
				.branchName(branchName)
				.themeName(themeName)
				.price(price)
				.themeStory(themeStory)
				.genre(genre)
				.runningTime(runningTime)
				.imagePath(themeImagePath)
				.build();
		
		try {
			themeRepository.save(theme);
		} catch (PersistenceException e) {
			return false;
		}
		
		return true;
		
	}
	
}
