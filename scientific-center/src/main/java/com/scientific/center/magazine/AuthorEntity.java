package com.scientific.center.magazine;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode
@Builder(toBuilder = true)
@Table(name = "AUTHOR")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "COUNTRY", nullable = false)
	private String country;

	@Column(name = "CITY", nullable = false)
	private String city;

	@Column(name = "EMAIL_ADDRESS", nullable = false)
	private String emailAddress;

	@Column(name = "MAIN_AUTHOR", nullable = false)
	private Boolean mainAuthor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESEARCH_PAPER_ID")
	private ResearchPaperEntity researchPaper;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<AreaOfInterestEntity> areasOfInterest;

}
