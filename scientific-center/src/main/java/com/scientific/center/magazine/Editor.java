package com.scientific.center.magazine;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Editor {

	private Long id;
	private String firstName;
	private String lastName;
	private Boolean isChief;

}
