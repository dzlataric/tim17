package com.scientific.center.magazine;

import java.util.List;

public interface MagazineService {

	List<Magazine> findAllMagazines();

	List<AreaOfScience> getAllAreasOfScience(Long magazineId);

	List<ResearchPaper> getResearchPapers(Long magazineId);

	Magazine findById(Long id);
}
