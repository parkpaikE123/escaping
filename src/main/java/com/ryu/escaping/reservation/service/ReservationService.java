package com.ryu.escaping.reservation.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ryu.escaping.admin.theme.domain.Theme;
import com.ryu.escaping.admin.theme.repository.ThemeRepository;
import com.ryu.escaping.reservation.domain.Reservation;
import com.ryu.escaping.reservation.dto.MineReservation;
import com.ryu.escaping.reservation.repository.ReservationRepository;

import jakarta.persistence.PersistenceException;

@Service
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final ThemeRepository themeRepository;
	
	public ReservationService(ReservationRepository reservationRepository, ThemeRepository themeRepository) {
		this.reservationRepository = reservationRepository;
		this.themeRepository = themeRepository;
	}
	// userId로 유저의 예약 목록 리스트, 및 테마의 정보 DTO
	public List<MineReservation> getMyReservationList(int userId) {
		List<Reservation> reservationList = reservationRepository.findByUserId(userId);
		List<MineReservation> myReservationList = new ArrayList<>();
		for(Reservation res:reservationList) {
			int themeId = res.getThemeId();
			Optional<Theme> optionalTheme = themeRepository.findById(themeId);
			if(optionalTheme.isPresent()) {
				Theme theme = optionalTheme.get();
				String themeName = theme.getThemeName();
				String imagePath = theme.getImagePath();
				int runningTime = theme.getRunningTime();
				int memberCount = res.getMemberCount();
				int reservationId = res.getId();
				Date reservationDate = res.getReservationDate();
				String reservationTime = res.getReservationTime();
				MineReservation myreservation = MineReservation.builder()
												.themeName(themeName)
												.reservationId(reservationId)
												.memberCount(memberCount)
												.reservationDate(reservationDate)
												.reservationTime(reservationTime)
												.imagePath(imagePath)
												.runningTime(runningTime)
												.build();
				myReservationList.add(myreservation);
			} 
		}
		return myReservationList;
	}
	// 결제시 예약 생성 및 PK얻어오기
	public int createReservationForPay(int userId
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
		
		reservation = reservationRepository.save(reservation);
		
		int reservationpk = reservation.getId();
		
		return reservationpk;
		
	}
	
	
	// 예약 삭제
	public boolean deleteReservation (int id) {
		Optional<Reservation>optionalReservation = reservationRepository.findById(id);
		if(optionalReservation.isPresent()) {
			Reservation reservation = optionalReservation.get();
			if(reservation.getId() != id) {
				return false;
			}
			try {
				reservationRepository.delete(reservation);
			} catch(PersistenceException e) {
				return false;
			}
		} else {
			return false;
		}
		return true;
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
