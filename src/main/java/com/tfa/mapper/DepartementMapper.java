package com.tfa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.tfa.dto.DepartementDto;
import com.tfa.entite.Departement;
import com.tfa.repository.DepartementRepo;

@Mapper
public interface DepartementMapper {

	public DepartementMapper MAPPER = Mappers.getMapper(DepartementMapper.class);
	
	public DepartementRepo toDto(Departement departement);
	public Departement toEntity(DepartementDto dto);
}
