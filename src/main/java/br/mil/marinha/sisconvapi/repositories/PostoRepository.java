package br.mil.marinha.sisconvapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.mil.marinha.sisconvapi.domain.Posto;

@Repository
public interface PostoRepository extends JpaRepository<Posto, Integer> {
	@Query("SELECT obj FROM Posto obj WHERE obj.desc_posto = :descricao")
	Posto findByDescricao(@Param("descricao") String descricao);
}
