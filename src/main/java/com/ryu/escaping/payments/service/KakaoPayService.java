package com.ryu.escaping.payments.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ryu.escaping.payments.vo.KakaoPayProperties;
import com.ryu.escaping.payments.vo.KakaoReadyResponse;

@Service
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
		String auth ="SECRET_KEY " + payProperties.getAdminKey();
		headers.set("Authorization", auth);
		headers.set("Content-Type", "application/json");
		return headers;
	}
	
	// 결제 완료 요청
	public KakaoReadyResponse kakaoPayReady() {
		Map<String, Object> parameters = new HashMap<>();
		
		parameters.put("cid", payProperties.getCid());
		parameters.put("partner_order_id", "ORDER_ID");
		parameters.put("partner_user_id", "USER_ID");
		parameters.put("item_name", "ITEM_NAME");
		parameters.put("quantity", "2200");
		parameters.put("total_amount", "1");
		parameters.put("vat_amount", "200");
		parameters.put("tax_free_amount", "0"); // 비과세 금액, 숫자는 문자열로 전달
		parameters.put("approval_url", "Web에서 등록한 URL/success");
		parameters.put("fail_url", "Web에서 등록한 URL/fail");
		parameters.put("cancel_url", "Web에서 등록한 URL/cancel");
		
		HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(parameters,this.getHeader());
		
		// 외부에 보낼 url
		RestTemplate resTemplate = new RestTemplate();
		
		kakaoReady = restTemplate.postForObject(
											"http://open-api.kakaopay.com/online/v1/payment/ready"
											, requestEntity
											, KakaoReadyResponse.class);
		return kakaoReady;
	}
	
	// 결제 완료 승인
	public KakaoApproveResponse approveResponse(String pgToken) {
		// 카카오 요청
		Map<String, String> parameters = new HashMap<>();
		parameters.put("cid", payProperties.getCid());
		parameters.put("tid", kakaoReady.getTid());
		parameters.put("partner_order_id", "파트너오더아이디");
		parameters.put("partner_uset_id", "주문유저아이디");
		parameters.put("pg_token", pgToken);
		
		// 파라미터, 헤더
	}
	
}
