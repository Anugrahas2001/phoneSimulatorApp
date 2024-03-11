package com.ff.phonesimulatorapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReponseStructure <T>{
	
	private int statusCode;
	private String message;
	private T data;

}
