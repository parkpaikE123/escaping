package com.ryu.escaping.payments.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoApproveResponse {

	private String aid;
	private String tid;
	private String cid;
	private String sid;
	private String partner_order_id;
	private String partner_user_id;
	private String apyment_method_type;
	private Amount amount;
	private String itme_name;
	private String item_code;
	private int quantity;
	private String create_at;
	private String approve_at;
	private String payload;
	
}
