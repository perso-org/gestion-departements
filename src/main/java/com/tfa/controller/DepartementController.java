package com.tfa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfa.dto.DepartementDto;
import com.tfa.service.DepartementService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/departements")
@RequiredArgsConstructor
@Slf4j
public class DepartementController {

	private final DepartementService service;

	@PostMapping("creation")
	public ResponseEntity<DepartementDto> creationDepartement(@RequestBody DepartementDto dto) {
		log.info("Création d'un départment...");
		DepartementDto creeDto = service.creationDepartement(dto);
		return new ResponseEntity<>(creeDto, HttpStatus.CREATED);
	}

	@PatchMapping("changement/{id}")
	public ResponseEntity<DepartementDto> misajourDepartement(@RequestBody DepartementDto dto, @PathVariable("id") Integer id) {
		DepartementDto departementDto = service.misajourDepartement(dto, id);
		
		if(departementDto == null) {
			throw new RuntimeException(String.format("Le departement avec l'identifiant %s n'existe pas...", id));
		}
		log.info("Departement mis à jour avec succès!");
		return ResponseEntity.ok(departementDto);
	}
	
	@PatchMapping("changement/code/{code}")
	public ResponseEntity<DepartementDto> misajourDepartement(@RequestBody DepartementDto dto, @PathVariable("code") String code) {
		DepartementDto departementDto = service.misajourDepartement(dto, code);
		
		if(departementDto == null) {
			throw new RuntimeException(String.format("Le departement avec le code %s n'existe pas...", code));
		}
		log.info("Departement mis à jour avec succès!");
		return ResponseEntity.ok(departementDto);
	}
	
	@GetMapping("code/{code}")
	public ResponseEntity<DepartementDto> obtenirDepartement(@PathVariable("code") String code) {
		DepartementDto dto = service.obtenirDepartement(code);
		
		if(dto == null) {
			throw new RuntimeException(String.format("Le departement avec le code %s n'existe pas...", code));
		}
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<DepartementDto> obtenirDepartementParId(@PathVariable("id") Integer id) {
		DepartementDto dto = service.obtenirDepartement(id);
		
		if(dto == null) {
			throw new RuntimeException(String.format("Le departement avec l'identifiant %s n'existe pas...", id));
		}
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<List<DepartementDto>> obtenirDepartements() {
		List<DepartementDto> dtos = service.obtenirDepartements();
		
		if(CollectionUtils.isEmpty(dtos)) {
			throw new RuntimeException("Il n'existe pas de departements");
		}
		return  ResponseEntity.ok(dtos);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> supprimerDepartement(@PathVariable("id") Integer id) {
		boolean isSupprime = service.supprimerDepartement(id);
		
		if(!isSupprime) {
			throw new RuntimeException(String.format("Le departement %s n'existe pas...", id));
		}
		return ResponseEntity.ok("Departement supprimé avec succès!");
	}
}
