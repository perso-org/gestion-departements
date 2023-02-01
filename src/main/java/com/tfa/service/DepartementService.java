package com.tfa.service;

import java.util.List;

import com.tfa.dto.DepartementDto;

public interface DepartementService {
 
	DepartementDto creationDepartement(DepartementDto dto);
	DepartementDto obtenirDepartement(Integer id);
	DepartementDto obtenirDepartement(String code);
	List<DepartementDto> obtenirDepartements();
	DepartementDto misajourDepartement(DepartementDto dto,Integer id);
	DepartementDto misajourDepartement(DepartementDto dto,String code);
	boolean supprimerDepartement(Integer id);
}
