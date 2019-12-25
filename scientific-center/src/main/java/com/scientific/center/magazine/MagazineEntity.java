package com.scientific.center.magazine;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "MAGAZINE")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MagazineEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "ISSN", nullable = false)
	private Integer issn;

	@Enumerated(EnumType.STRING)
	@Column(name = "MEMBERSHIP_FEE_TYPE")
	private MembershipFeeType membershipFeeType;

	@OneToMany(mappedBy = "magazine", cascade = CascadeType.ALL)
	private List<AreaOfScienceEntity> areasOfScience;

	@OneToMany(mappedBy = "magazine", cascade = CascadeType.ALL)
	private List<EditorEntity> editors;

}
