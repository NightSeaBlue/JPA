package com.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "board")
public class Board {
	// 멤버변수 작성
	@Id
	//@GeneratedValue(strategy = )
	private int seq;
	
	private String title;
	
	private String writer;
	
	private String content;
	
	private Date regdate;
}
