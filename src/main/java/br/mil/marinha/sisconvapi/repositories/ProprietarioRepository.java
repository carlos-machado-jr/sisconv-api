package br.mil.marinha.sisconvapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.mil.marinha.sisconvapi.domain.Proprietarios;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietarios, Integer> {
	@Query("SELECT obj FROM Proprietarios obj WHERE obj.nip = :nip")
	Proprietarios findByNip(@Param("nip") String nip);
	
	@Query("SELECT obj FROM Proprietarios obj WHERE obj.id = :id and obj.ativo = :ativo")
	Optional<Proprietarios> findByIdAndAtivo(@Param("id") Integer id, @Param("ativo") boolean ativo);
	
	
	@Query("SELECT obj FROM Proprietarios obj where obj.ativo = true")
	List<Proprietarios> findAllActivated();
	
	@Query("SELECT obj FROM Proprietarios obj where obj.ativo = false")
	List<Proprietarios> findAllDisabled();
}
