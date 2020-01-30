package com.scientific.center.magazine;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
class MagazineServiceImpl implements MagazineService {

	private final MagazineRepository magazineRepository;

	@Override
	public List<Magazine> findAllMagazines() {
		return magazineRepository.findAll()
			.stream()
			.map(this::mapMagazine)
			.collect(Collectors.toList());
	}

	@Override
	public List<AreaOfScience> getAllAreasOfScience(final Long magazineId) {
		return mapAreasOfScience(magazineRepository.findById(magazineId).orElseThrow());
	}

	@Override
	public List<ResearchPaper> getResearchPapers(final Long magazineId) {
		return magazineRepository.findById(magazineId).orElseThrow().getResearchPapers().stream()
			.map(this::mapResearchPapers)
			.collect(Collectors.toList());
	}

	private ResearchPaper mapResearchPapers(final ResearchPaperEntity rp) {
		return ResearchPaper.builder()
			.id(rp.getId())
			.title(rp.getTitle())
			.paperAbstract(rp.getPaperAbstract())
			.authors(rp.getAuthors().stream()
				.map(this::mapAuthors)
				.collect(Collectors.toList()))
			.build();
	}

	private Author mapAuthors(final AuthorEntity a) {
		return Author.builder()
			.id(a.getId())
			.firstName(a.getFirstName())
			.lastName(a.getLastName())
			.mainAuthor(a.getMainAuthor())
			.build();
	}

	@Override
	public Magazine findById(final Long id) {
		return mapMagazine(magazineRepository.findById(id).orElseThrow());
	}

	private Magazine mapMagazine(final MagazineEntity me) {
		return Magazine.builder()
			.id(me.getId())
			.title(me.getTitle())
			.issn(me.getIssn())
			.membershipFeeType(me.getMembershipFeeType())
			.editors(mapEditors(me))
			.areasOfScience(mapAreasOfScience(me))
			.paymentTypes(mapPaymentTypes(me))
			.build();
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

	private List<PaymentType> mapPaymentTypes(final MagazineEntity me) {
		return me.getPaymentTypes().stream()
			.map(pt -> PaymentType.builder()
				.id(pt.getId())
				.name(pt.getName())
				.build())
			.collect(Collectors.toList());
	}

}
