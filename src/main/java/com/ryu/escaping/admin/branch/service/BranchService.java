package com.ryu.escaping.admin.branch.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ryu.escaping.admin.branch.repository.BranchRepository;

@Service
public class BranchService {

	public final BranchRepository branchRepository;
	
	public BranchService(BranchRepository branchRepository) {
		this.branchRepository = branchRepository;
	}
	
	public boolean addBranch(String branchName, MultipartFile branchImage, String location) {
		
	}
	
}
