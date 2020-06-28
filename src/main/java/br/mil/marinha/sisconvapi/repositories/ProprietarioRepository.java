package br.mil.marinha.sisconvapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.mil.marinha.sisconvapi.domain.Proprietarios;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietarios, Integer> {
	@Query("SELECT obj FROM Proprietarios obj WHERE obj.nip = :nip")
	Proprietarios findByNip(@Param("nip") String nip);
}
