package br.mil.marinha.sisconvapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mil.marinha.sisconvapi.domain.Montadora;

@Repository
public interface MontadoraRepository extends JpaRepository<Montadora, Integer>{

}
