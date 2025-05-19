package com.ryu.escaping.payments;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.escaping.payments.service.KakaoPayService;
import com.ryu.escaping.payments.vo.KakaoReadyResponse;

@RestController
@RequestMapping("/payment")
public class KakaoPay {

	private final KakaoPayService kakaoPayService; 
	public KakaoPay(KakaoPayService kakaoPayService) {
		this.kakaoPayService = kakaoPayService;
	}
	
	// 결제 준비 API
	@PostMapping("/ready")
	public KakaoReadyResponse readyToKakaoPay() {
		return kakaoPayService.kakaoPayReady();
	}
	
}
