package br.mil.marinha.sisconvapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.mil.marinha.sisconvapi.domain.Cor;

@Repository
public interface CorRepository extends JpaRepository<Cor, Integer>{
	@Query("SELECT obj FROM Cor obj WHERE obj.desc_cor = :descricao")
	Cor findByDescricao(@Param("descricao") String descricao);
}
