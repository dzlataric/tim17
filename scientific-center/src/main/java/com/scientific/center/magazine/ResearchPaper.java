package com.scientific.center.magazine;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ResearchPaper {

	private Long id;
	private String title;
	private String paperAbstract;
	private List<Author> authors;

}
