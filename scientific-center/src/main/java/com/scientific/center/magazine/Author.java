package com.scientific.center.magazine;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Author {

	private Long id;
	private String firstName;
	private String lastName;
	private String country;
	private String city;
	private String emailAddress;
	private Boolean mainAuthor;
	private List<AreaOfInterest> areasOfInterest;

}
