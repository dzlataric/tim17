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
				.editors(mapEditors(me))
				.areasOfScience(mapAreasOfScience(me))
				.build())
			.collect(Collectors.toList());
	}

	@Override
	public List<AreaOfScience> getAllAreasOfScience(final Long magazineId) {
		return mapAreasOfScience(magazineRepository.findById(magazineId).orElseThrow());
	}

	private List<AreaOfScience> mapAreasOfScience(final MagazineEntity magazineEntity) {
		return magazineEntity.getAreasOfScience()
			.stream()
			.map(aos -> AreaOfScience.builder()
				.id(aos.getId())
				.name(aos.getName())
				.build())
			.collect(Collectors.toList());
	}

	private List<Editor> mapEditors(final MagazineEntity magazineEntity) {
		return magazineEntity.getEditors()
			.stream()
			.map(e -> Editor.builder()
				.id(e.getId())
				.firstName(e.getFirstName())
				.lastName(e.getLastName())
				.isChief(e.getChiefEditor())
				.areaOfScience(e.getAreaOfScience() != null ? AreaOfScience.builder()
					.id(e.getAreaOfScience().getId())
					.name(e.getAreaOfScience().getName())
					.build() : null)
				.build())
			.collect(Collectors.toList());
	}
}
