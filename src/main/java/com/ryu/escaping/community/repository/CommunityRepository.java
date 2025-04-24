package com.ryu.escaping.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryu.escaping.community.domain.Community;

public interface CommunityRepository extends JpaRepository<Community,Integer> {

}
