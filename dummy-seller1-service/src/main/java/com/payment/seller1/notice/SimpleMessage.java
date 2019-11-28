package com.payment.seller1.notice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "system_message")
public class SimpleMessage {

	@Id
	@Column
	private Integer id;
	@Column
	private String content;
}
