package com.ryu.escaping.admin.theme.service;

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
	private BranchRepository branchRepository;
	public ThemeService(ThemeRepository themeRepository, BranchRepository branchRepository) {
		this.themeRepository = themeRepository;
		this.branchRepository = branchRepository;
	}
	
	public int countByTheme(String branchName) {
		return themeRepository.countByBranchName(branchName);
	}
	
	public boolean addTheme(String branchName
							,String themeName
							,int price
							,String genre
							,int runningTime
							,MultipartFile imagefile) {
		
		String themeImagePath = FileManager.saveTheme(imagefile);
		
		
		Theme theme = Theme.builder()
				.branchName(branchName)
				.themeName(themeName)
				.price(price)
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
