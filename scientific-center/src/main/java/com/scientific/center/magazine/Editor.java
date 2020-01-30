package com.scientific.center.magazine;

import org.springframework.lang.Nullable;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Editor {

	private Long id;
	private String firstName;
	private String lastName;
	private Boolean isChief;
	@Nullable
	private AreaOfScience areaOfScience;

}
