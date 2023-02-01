package com.tfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfa.entite.Departement;

public interface DepartementRepo extends JpaRepository<Departement, Integer>{

	Departement findByCode(String code);
}
