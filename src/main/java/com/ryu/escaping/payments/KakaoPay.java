package com.ryu.escaping.payments;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.escaping.payments.service.KakaoPayService;
import com.ryu.escaping.payments.vo.KakaoApproveResponse;
import com.ryu.escaping.payments.vo.KakaoReadyResponse;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/pay")
public class KakaoPay {

	private final KakaoPayService kakaoPayService; 
	public KakaoPay(KakaoPayService kakaoPayService) {
		this.kakaoPayService = kakaoPayService;
	}
	
	// 결제 요청 API
	@PostMapping("/ready")
	public KakaoReadyResponse readyToKakaoPay(HttpSession session
											,@RequestParam String itemName
											,@RequestParam int quantity
											,@RequestParam int totalAmount
											,@RequestParam int vatAmount
											,@RequestParam int taxFree) {
		String userId = (String)session.getId();
		Date today = new Date();
		String stringDate = ""+today;
		String orderId = (String)session.getId() + stringDate;
		return kakaoPayService.kakaoPayReady(orderId, userId, itemName, quantity, totalAmount, vatAmount, taxFree);
	}
	
	// 결제 성공
	@PostMapping("/success")
	public ResponseEntity<KakaoApproveResponse> afterPayRequest(@RequestParam("pg_token") String pgToken) {
		KakaoApproveResponse KakaoApprove = kakaoPayService.approveResponse(pgToken);
		return new ResponseEntity<>(KakaoApprove, HttpStatus.OK);
	}
	
//	// 결제 진행 중 취소
//	@GetMapping("/cancel")
//	public void cancel() {
//		throw new BusinessLogicException(ExceptionCode.PAY_CANCEL);
//	}
//	
//	// 결제실패
//	@GetMapping("/fail")
//	public void fail() {
//		throw new BusinessLogicException(ExceptionCode.PAY_FAILED);
//	}
}
