package com.ryu.escaping.reservation.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MineReservation {

	private int reservationId;
	private Date reservationDate;
	private String reservationTime;
	private String imagePath;
	private int runningTime;
	private String themeName;
	private int memberCount;
	
}
