package com.scientific.center.web;

import com.scientific.center.magazine.AreaOfScience;
import com.scientific.center.magazine.Magazine;
import com.scientific.center.magazine.MagazineService;
import com.scientific.center.magazine.ResearchPaper;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/magazine")
@AllArgsConstructor
public class MagazineController {

	private final MagazineService magazineService;

	@GetMapping
	public ResponseEntity<List<Magazine>> findAll() {
		return new ResponseEntity<>(magazineService.findAllMagazines(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Magazine> findByMagazineId(@PathVariable Long id) {
		return new ResponseEntity<>(magazineService.findById(id), HttpStatus.OK);
	}

	@GetMapping("/areas-of-science/{magazineId}")
	public ResponseEntity<List<AreaOfScience>> findAreasOfScienceForMagazine(@PathVariable Long magazineId) {
		return new ResponseEntity<>(magazineService.getAllAreasOfScience(magazineId), HttpStatus.OK);
	}

	@GetMapping("/research-papers/{magazineId}")
	public ResponseEntity<List<ResearchPaper>> findResearchPapersByMagazineId(@PathVariable Long magazineId) {
		return new ResponseEntity<>(magazineService.getResearchPapers(magazineId), HttpStatus.OK);
	}

}
