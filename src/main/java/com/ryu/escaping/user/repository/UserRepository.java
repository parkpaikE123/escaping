package com.ryu.escaping.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryu.escaping.user.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
