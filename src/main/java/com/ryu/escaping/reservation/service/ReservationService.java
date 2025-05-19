package com.ryu.escaping.reservation.service;

import java.sql.Date;

import org.springframework.stereotype.Service;

import com.ryu.escaping.reservation.domain.Reservation;
import com.ryu.escaping.reservation.repository.ReservationRepository;

import jakarta.persistence.PersistenceException;

@Service
public class ReservationService {

	private final ReservationRepository reservationRepository;
	
	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}
	
	// 예약 생성
	public boolean addReservation(int userId
								,int themeId
								,Date reservationDate
								,String reservationTime
								,int memberCount) {
		Reservation reservation = Reservation.builder()
								.userId(userId)
								.themeId(themeId)
								.reservationDate(reservationDate)
								.reservationTime(reservationTime)
								.memberCount(memberCount)
								.build();
		try {
			reservationRepository.save(reservation);
		} catch(PersistenceException e){
			return false;
		}
		
		return true;
	}
	
	
	
	
	
}
