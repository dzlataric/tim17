package com.scientific.center.web;

import com.scientific.center.magazine.Magazine;
import com.scientific.center.magazine.MagazineService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/magazine")
public class MagazineController {

	private final MagazineService magazineService;

	@Autowired
	public MagazineController(final MagazineService magazineService) {
		this.magazineService = magazineService;
	}

	@GetMapping
	public ResponseEntity<List<Magazine>> findAll() {
		return new ResponseEntity<>(magazineService.findAllMagazines(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Magazine> findAll(@PathVariable Long id) {
		return new ResponseEntity<>(magazineService.findById(id), HttpStatus.OK);
	}

}
