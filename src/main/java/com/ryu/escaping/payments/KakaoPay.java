package com.ryu.escaping.payments;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryu.escaping.common.MD5HashingEncoder;
import com.ryu.escaping.payments.service.KakaoPayService;
import com.ryu.escaping.payments.vo.KakaoApproveResponse;
import com.ryu.escaping.payments.vo.KakaoReadyResponse;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pay")
public class KakaoPay {

	private final KakaoPayService kakaoPayService; 
	public KakaoPay(KakaoPayService kakaoPayService) {
		this.kakaoPayService = kakaoPayService;
	}
	
	// 결제 요청 API
	@PostMapping("/ready")
	@ResponseBody
	public KakaoReadyResponse readyToKakaoPay(HttpSession session
											,@RequestParam String itemName
											,@RequestParam int quantity
											,@RequestParam int totalAmount
											,@RequestParam int vatAmount
											,@RequestParam int taxFree) {
		
		String userId = (Integer)session.getAttribute("userId")+"";
		String orderId = MD5HashingEncoder.encode(userId);
		return kakaoPayService.kakaoPayReady(orderId,userId,itemName, quantity, totalAmount, vatAmount, taxFree);
	}
	
	// 결제 성공
	@GetMapping("/success")
	@ResponseBody
	public KakaoApproveResponse afterPayRequest(@RequestParam("pg_token") String pgToken
																,HttpSession session) {
		String userId = (Integer)session.getAttribute("userId")+"";
		String orderId = MD5HashingEncoder.encode(userId);
		KakaoApproveResponse KakaoApprove = kakaoPayService.approveResponse(pgToken,userId,orderId);
		return KakaoApprove;
		
	}
	
}
