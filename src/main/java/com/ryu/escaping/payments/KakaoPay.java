package com.ryu.escaping.payments;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryu.escaping.payments.service.KakaoPayService;
import com.ryu.escaping.payments.vo.KakaoApproveResponse;
import com.ryu.escaping.payments.vo.KakaoReadyResponse;
import com.ryu.escaping.reservation.service.ReservationService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pay")
public class KakaoPay {

	private final KakaoPayService kakaoPayService; 
	private final ReservationService reservationService; 
	public KakaoPay(KakaoPayService kakaoPayService, ReservationService reservationService) {
		this.reservationService = reservationService;
		this.kakaoPayService = kakaoPayService;
	}
	
	// 결제 요청 API
	@PostMapping("/ready")
	@ResponseBody
	public KakaoReadyResponse readyToKakaoPay(HttpSession session
											,@RequestParam int themeId
											,@RequestParam Date reservationDate
											,@RequestParam String reservationTime
											,@RequestParam int memberCount
											,@RequestParam String itemName
											,@RequestParam int quantity
											,@RequestParam int totalAmount
											,@RequestParam int vatAmount
											,@RequestParam int taxFree) {
		
		int userId = (Integer)session.getAttribute("userId");
		int reservationPk = reservationService.createReservationForPay(userId, themeId, reservationDate, reservationTime, memberCount);
		
		String stringUserId = (Integer)session.getAttribute("userId")+"";
		String orderId = reservationPk + "";
		return kakaoPayService.kakaoPayReady(orderId,stringUserId,itemName, quantity, totalAmount, vatAmount, taxFree);
	}
	
	// 결제 성공
	@GetMapping("/success")
	public String afterPayRequest(@RequestParam("pg_token") String pgToken
								,HttpSession session
								,@RequestParam String orderId) {
		String userId = (Integer)session.getAttribute("userId")+"";
		
		KakaoApproveResponse KakaoApprove = kakaoPayService.approveResponse(pgToken,userId,orderId);
		return "redirect:/reservation/mine";
		
	}
	
}
