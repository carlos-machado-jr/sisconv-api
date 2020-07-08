package br.mil.marinha.sisconvapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.mil.marinha.sisconvapi.domain.Usuarios;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer>{
	@Query("SELECT obj FROM Usuarios obj WHERE obj.nome_usuario = :nome_usuario")
	Usuarios findByNomeUsuario(@Param("nome_usuario") String nome_usuario);
}
