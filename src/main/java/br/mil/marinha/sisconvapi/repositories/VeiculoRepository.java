package br.mil.marinha.sisconvapi.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.mil.marinha.sisconvapi.domain.Veiculos;

@Repository
public interface VeiculoRepository extends  JpaRepository<Veiculos, Integer>{
	@Query("SELECT obj FROM Veiculos obj WHERE obj.id = :id and obj.ativo = true")
	Optional<Veiculos> findByIdAndAtivo(@Param("id") Integer id);
	
	@Query("SELECT obj FROM Veiculos obj WHERE obj.proprietario.id = :id_proprietario")
	Set<Veiculos> findByIdProprietario(@Param("id_proprietario") Integer id);
	
	@Query("SELECT obj FROM Veiculos obj where obj.ativo = true")
	List<Veiculos> findByAllAtivos();
}
