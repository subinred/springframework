package com.mycompany.webapp.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class Ch03Dto {
	private String param1;
	private int param2;
	private double param3;
	private boolean param4;
	private @DateTimeFormat(pattern = "yyyy-mm-dd") Date param5;
}
