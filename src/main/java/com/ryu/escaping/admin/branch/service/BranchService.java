package com.ryu.escaping.admin.branch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ryu.escaping.admin.branch.domain.Branch;
import com.ryu.escaping.admin.branch.dto.BranchForAdmin;
import com.ryu.escaping.admin.branch.repository.BranchRepository;
import com.ryu.escaping.admin.theme.domain.Theme;
import com.ryu.escaping.admin.theme.repository.ThemeRepository;
import com.ryu.escaping.common.FileManager;

import jakarta.persistence.PersistenceException;

@Service
public class BranchService {

	private final BranchRepository branchRepository;
	private final ThemeRepository themeRepository;
	
	public BranchService(BranchRepository branchRepository, ThemeRepository themeRepository) {
		this.branchRepository = branchRepository;
		this.themeRepository = themeRepository;
	}

	
	public int countThemeByBranchId(int branchId) {
		int count = themeRepository.countByBranchId(branchId);
		return count;
	}
	
	public List<Branch> getListBySearch(String locationKey) {
		
		List<Branch> branchList = branchRepository.findByLocationKey(locationKey);
		return branchList;
		
	}
	
	
	
	public boolean deleteBranch(int id) {
		
		Optional<Branch> optionalBranch = branchRepository.findById(id);
		
		if(optionalBranch.isPresent()) {
			
		Branch branch = optionalBranch.get();
			
			// 삭제 실패
			if(branch.getId() != id) {
				return false;
			}
			// 지점 사진파일 삭제
			FileManager.removeBranchFile(branch.getBranchPath());
			
			List<Theme> themeList = themeRepository.findByBranchId(id);
			
			for(Theme theme:themeList) {
				FileManager.removeThemeFile(theme.getImagePath());
			}
			try {
				
				themeRepository.deleteByBranchId(id);
				branchRepository.delete(branch);
				
			} catch(PersistenceException e) {
				return false;
			}
		
		} else {
			return false;
		}
		
		return true;
	}
	
	public Branch getBranchById(int id) {
		Optional<Branch>optionalBranch = branchRepository.findById(id);
		
		if(optionalBranch.isPresent()) {
			Branch branch = optionalBranch.get();
			return branch;
		} else {
			return null;
		}
		
	}
	
	public List<BranchForAdmin> getBranchList() {
				
			List<Branch> branchList = branchRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
			List <BranchForAdmin> branchDtoList = new ArrayList <>();
			for(Branch branch:branchList) {
				
				int count = themeRepository.countByBranchId(branch.getId());
				
				BranchForAdmin card = BranchForAdmin.builder()
								.branchId(branch.getId())
								.branchPath(branch.getBranchPath())
								.themeCount(count)
								.location(branch.getLocation())
								.name(branch.getName())
								.build();
			
				branchDtoList.add(card);
			}		
			return branchDtoList;
				
		}
	
	public boolean addBranch(
							String branchName
							, MultipartFile imageFile
							, String location
							, String locationKey) {
		String branchImagePath = FileManager.saveBranch(imageFile);
		
		
		Branch branch = Branch.builder()
				.name(branchName)
				.location(location)
				.locationKey(locationKey)
				.branchPath(branchImagePath)
				.build();
		try {
			branchRepository.save(branch);
		} catch (PersistenceException e) {
			return false;
		}
		
		return true;
		
	}
	
}
