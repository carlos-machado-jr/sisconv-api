package br.mil.marinha.sisconvapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.mil.marinha.sisconvapi.domain.Montadora;

@Repository
public interface MontadoraRepository extends JpaRepository<Montadora, Integer>{
	@Query("SELECT obj FROM Montadora obj WHERE obj.desc_montadora = :descricao")
	Montadora findByDescricao(@Param("descricao") String descricao);
}
