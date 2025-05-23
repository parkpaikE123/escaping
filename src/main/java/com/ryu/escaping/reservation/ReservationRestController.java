package com.ryu.escaping.reservation;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.escaping.reservation.service.ReservationService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/reservation")
public class ReservationRestController {

	private final ReservationService reservationService;
	public ReservationRestController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	// 예약 삭제
	@DeleteMapping("/delete")
	public Map<String, String> deleteReservation(@RequestParam int id) {
		Map<String, String> resultMap = new HashMap<>();
		if(reservationService.deleteReservation(id)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
	// 예약 생성
	public Map<String, String> createReservation(@RequestParam int themeId
												,@RequestParam Date reservationDate
												,@RequestParam String reservationTime
												,@RequestParam int memberCount
												,HttpSession session) {
		Map<String, String> resultMap = new HashMap<>();
		int userId = (Integer)session.getAttribute("userId");
		
		if(reservationService.addReservation(userId, themeId, reservationDate, reservationTime, memberCount)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
}
