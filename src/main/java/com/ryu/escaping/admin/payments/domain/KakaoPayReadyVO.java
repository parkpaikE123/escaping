package com.ryu.escaping.admin.payments.domain;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class KakaoPayReadyVO {
	
	private String tid;
	private String next_redirect_pc_url;
	private String partner_order_id;
	
}
