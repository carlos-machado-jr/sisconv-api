package br.mil.marinha.sisconvapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.mil.marinha.sisconvapi.domain.Permissoes;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissoes, Integer>{
	@Query("SELECT obj FROM Permissoes obj WHERE obj.desc_permissoes = :descricao")
	Optional<Permissoes> findByDescricao(@Param("descricao") String descricao);
}
