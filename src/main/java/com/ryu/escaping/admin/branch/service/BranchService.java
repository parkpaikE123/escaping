package com.ryu.escaping.admin.branch.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ryu.escaping.admin.branch.domain.Branch;
import com.ryu.escaping.admin.branch.repository.BranchRepository;
import com.ryu.escaping.common.FileManager;

import jakarta.persistence.PersistenceException;

@Service
public class BranchService {

	private final BranchRepository branchRepository;
	
	public BranchService(BranchRepository branchRepository) {
		this.branchRepository = branchRepository;
	}
	public List<Branch> getBranch(
							) {
				
				List<Branch> branch = branchRepository.findAll();
				
					return branch;		
				
				}
	
	public boolean addBranch(
							String branchName
							, MultipartFile imageFile
							, String location) {
		String branchImagePath = FileManager.saveBranch(imageFile);
		
		
		Branch branch = Branch.builder()
				.name(branchName)
				.location(location)
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
