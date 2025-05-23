package com.ryu.escaping.reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ryu.escaping.reservation.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Integer>{

	public List<Reservation> findByUserId(int userId);
	
}
