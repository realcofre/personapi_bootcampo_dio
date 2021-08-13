package br.com.aracatidigital.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aracatidigital.personapi.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
