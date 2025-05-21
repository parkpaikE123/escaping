package com.ryu.escaping.payments.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ryu.escaping.payments.vo.KakaoApproveResponse;
import com.ryu.escaping.payments.vo.KakaoPayProperties;
import com.ryu.escaping.payments.vo.KakaoReadyResponse;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class KakaoPayService {

	private final KakaoPayProperties payProperties;
	public KakaoPayService(KakaoPayProperties payProperties) {
		this.payProperties = payProperties;
	}
	private RestTemplate restTemplate = new RestTemplate();
	private KakaoReadyResponse kakaoReady;
	
	// 결제 준비
	public HttpHeaders getHeader() {
		
		HttpHeaders headers = new HttpHeaders();
		String auth ="SECRET_KEY DEV189C85C7AA3625A7AB277CD653E148C650730";
		headers.set("Authorization", auth);
		headers.set("Content-Type", "application/json");
		return headers;
	}
	
	// 결제 완료 요청
	public KakaoReadyResponse kakaoPayReady(
											String orderId
											,String userId
											,String itemName
											,int quantity
											,int totalAmount
											,int vatAmount
											,int taxFree) {
		
		Map<String, Object> parameters = new HashMap<>();
		
		parameters.put("cid", "TC0ONETIME");
		parameters.put("partner_order_id", orderId);
		parameters.put("partner_user_id", userId);
		parameters.put("item_name", itemName+"");
		parameters.put("quantity", quantity+"");
		parameters.put("total_amount", totalAmount+"");
		parameters.put("vat_amount", vatAmount+"");
		parameters.put("tax_free_amount", taxFree+""); 
		parameters.put("approval_url", "http://localhost:8080/pay/success");
		parameters.put("fail_url", "http://localhost:8080/fail");
		parameters.put("cancel_url", "http://localhost:8080/cancel");
		
		HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(parameters,this.getHeader());
		
		// 외부에 보낼 url
		RestTemplate restTemplate = new RestTemplate();
		
		kakaoReady = restTemplate.postForObject(
											"https://open-api.kakaopay.com/online/v1/payment/ready"
											, requestEntity
											, KakaoReadyResponse.class);
		return kakaoReady;
	}
	
	// 결제 완료 승인
	public KakaoApproveResponse approveResponse(String pgToken
												,String userId
												,String orderId) {
		
		// 카카오 요청
		Map<String, String> parameters = new HashMap<>();
		parameters.put("cid", "TC0ONETIME");
		parameters.put("tid", kakaoReady.getTid());
		parameters.put("partner_order_id", orderId);
		parameters.put("partner_user_id", userId);
		parameters.put("pg_token", pgToken);
		
		// 파라미터, 헤더
		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(parameters,this.getHeader());
		
		// 외부에 보내 url
		RestTemplate restTemplate = new RestTemplate();
		
		KakaoApproveResponse approveResponse = restTemplate.postForObject(
												"https://open-api.kakaopay.com/online/v1/payment/approve"
												, requestEntity
												, KakaoApproveResponse.class);

		return approveResponse;
	}
	
}
