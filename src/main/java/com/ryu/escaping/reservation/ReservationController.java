package com.ryu.escaping.reservation;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ryu.escaping.admin.theme.service.ThemeService;
import com.ryu.escaping.reservation.dto.MineReservation;
import com.ryu.escaping.reservation.service.ReservationService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	private final ReservationService reservationService;
	private final ThemeService themeService;
	public ReservationController(ReservationService reservationService, ThemeService themeService) {
		this.reservationService = reservationService;
		this.themeService = themeService;
	}
	
	// 나의 예약목록
	@GetMapping("/mine")
	public String mineReservationList(HttpSession session
									,Model model) {
		int userId =(Integer)session.getAttribute("userId");
		List<MineReservation> reservationList = reservationService.getMyReservationList(userId);
		model.addAttribute("reservationList",reservationList);
		return "/user/reservation";
	}
	
}
