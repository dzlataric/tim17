package com.scientific.center.magazine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "EDITOR")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EditorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "CHIEF_EDITOR", nullable = false)
	private Boolean chiefEditor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAGAZINE_ID")
	private MagazineEntity magazine;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "AREA_OF_SCIENCE_ID")
	private AreaOfScienceEntity areaOfScience;

}
