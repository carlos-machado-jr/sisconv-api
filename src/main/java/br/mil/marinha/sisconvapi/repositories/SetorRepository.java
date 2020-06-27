package br.mil.marinha.sisconvapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mil.marinha.sisconvapi.domain.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer> {

}
