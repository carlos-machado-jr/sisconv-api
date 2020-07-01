package br.mil.marinha.sisconvapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.mil.marinha.sisconvapi.domain.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer> {
	@Query("SELECT obj FROM Setor obj WHERE obj.desc_setor = :descricao")
	Setor findByDescricao(@Param("descricao") String descricao);
}
