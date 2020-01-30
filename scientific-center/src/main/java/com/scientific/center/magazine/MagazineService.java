package com.scientific.center.magazine;

import java.util.List;

public interface MagazineService {

	List<Magazine> findAllMagazines();

	List<AreaOfScience> getAllAreasOfScience(Long magazineId);

	Magazine findById(Long id);
}
