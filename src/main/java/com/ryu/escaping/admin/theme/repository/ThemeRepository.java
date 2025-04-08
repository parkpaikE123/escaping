package com.ryu.escaping.admin.theme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryu.escaping.admin.theme.domain.Theme;


public interface ThemeRepository extends JpaRepository<Theme, Integer> {

}
