package com.scientific.center.magazine;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class MagazineServiceImpl implements MagazineService {

	private final MagazineRepository magazineRepository;

	@Autowired
	public MagazineServiceImpl(final MagazineRepository magazineRepository) {
		this.magazineRepository = magazineRepository;
	}

	@Override
	public List<Magazine> findAllMagazines() {
		return magazineRepository.findAll()
			.stream()
			.map(me -> Magazine.builder()
				.id(me.getId())
				.title(me.getTitle())
				.issn(me.getIssn())
				.membershipFeeType(me.getMembershipFeeType())
				.editors(me.getEditors()
					.stream()
					.map(e -> Editor.builder()
						.id(e.getId())
						.firstName(e.getFirstName())
						.lastName(e.getLastName())
						.isChief(e.getChiefEditor())
						.build())
					.collect(Collectors.toList()))
				.areasOfScience(me.getAreasOfScience()
					.stream()
					.map(aos -> AreaOfScience.builder()
						.id(aos.getId())
						.name(aos.getName())
						.build())
					.collect(Collectors.toList()))
				.build())
			.collect(Collectors.toList());
	}
}
