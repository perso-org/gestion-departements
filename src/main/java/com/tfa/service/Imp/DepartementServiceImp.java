package com.tfa.service.Imp;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tfa.dto.DepartementDto;
import com.tfa.entite.Departement;
import com.tfa.repository.DepartementRepo;
import com.tfa.service.DepartementService;
import java.util.Collections;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class DepartementServiceImp implements DepartementService {

	private final DepartementRepo repo;
	private final ModelMapper mapper;
	
	@Override
	public DepartementDto creationDepartement(DepartementDto dto) {
		
		Departement departement = mapper.map(dto, Departement.class);
		
		Departement creeDepartement = repo.save(departement);
		
		return  mapper.map(creeDepartement, DepartementDto.class);
	}

	@Override
	public DepartementDto misajourDepartement(DepartementDto dto, Integer id) {
		
		Objects.requireNonNull(id);
		Objects.requireNonNull(dto);
		Optional<Departement> departement = repo.findById(id);
		if(departement.isEmpty()) {
			return null;
		}
		Departement dep = departement.get();
		dep.setCode(dto.getCode() == null ? dep.getCode() : dto.getCode());
		dep.setNom(dto.getNom() == null ? dep.getNom() : dto.getNom());
		dep.setDescription(dto.getDescription() == null ? dep.getDescription() : dto.getDescription()); 
		
		Departement saved =  repo.save(dep);
		
		return mapper.map(saved, DepartementDto.class);
	}

	@Override
	public DepartementDto misajourDepartement(DepartementDto dto, String code) {
		Objects.requireNonNull(code);
		Objects.requireNonNull(dto);
		
		Departement departement = repo.findByCode(code);
		if(departement == null) {
			return null;
		}

		departement.setCode(dto.getCode() == null ? departement.getCode() : dto.getCode());
		departement.setNom(dto.getNom() == null ? departement.getNom() : dto.getNom());
		departement.setDescription(dto.getDescription() == null ? departement.getDescription() : dto.getDescription()); 
		
		Departement saved =  repo.save(departement);
		
		return mapper.map(saved, DepartementDto.class);
	}

	@Override
	public DepartementDto obtenirDepartement(Integer id) {
		Objects.requireNonNull(id);
		log.info("Recherche departement par id... {}",id);
		Optional<Departement> departement = repo.findById(id);
		
		return departement.map(dep -> mapper.map(dep, DepartementDto.class)).orElse(null);
	}

	@Override
	public DepartementDto obtenirDepartement(String code) {
		Objects.requireNonNull(code);
		log.info("Recherche departement par code... {}",code);
		Departement departement = repo.findByCode(code);
		
		return departement == null ? null : mapper.map(departement, DepartementDto.class);
	}

	@Override
	public List<DepartementDto> obtenirDepartements() {
		List<Departement> departements = repo.findAll();
		if(CollectionUtils.isEmpty(departements)) {
			return Collections.emptyList();
		}
		return departements.stream().map(dep -> mapper.map(dep, DepartementDto.class)).toList();
	}

	@Override
	public boolean supprimerDepartement(Integer id) {
		Objects.requireNonNull(id);
		Optional<Departement> departement = repo.findById(id);
		
		if(departement.isEmpty()) {
			return false;
		}
		repo.deleteById(id);
		return true;
	}

}
