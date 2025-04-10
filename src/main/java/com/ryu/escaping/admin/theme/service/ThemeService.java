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
	private BranchRepository branchRepository;
	public ThemeService(ThemeRepository themeRepository, BranchRepository branchRepository) {
		this.themeRepository = themeRepository;
		this.branchRepository = branchRepository;
	}
	
	public int countTheme(int branchId) {
		return themeRepository.countByBranchId(branchId);
	}
	
	public List<Theme> getTheme() {
		List<Theme> themeList = themeRepository.findAll();
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
							,int runningTime
							,MultipartFile imagefile) {
		
		String themeImagePath = FileManager.saveTheme(imagefile);
		
		Theme theme = Theme.builder()
				.branchId(branchId)
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
