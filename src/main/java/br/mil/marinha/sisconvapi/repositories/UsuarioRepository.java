package br.mil.marinha.sisconvapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.mil.marinha.sisconvapi.domain.Usuarios;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer>{
	@Query("SELECT obj FROM Usuarios obj WHERE obj.nome_usuario = :nome_usuario")
	Usuarios findByNomeUsuario(@Param("nome_usuario") String nome_usuario);
	
	@Query("SELECT obj FROM Usuarios obj WHERE obj.permissoes.desc_permissoes = :permissao and obj.ativo = true")
	List<Usuarios> findAllUsuarioComum(@Param("permissao") String permissao);
	
	@Query("SELECT obj FROM Usuarios obj WHERE obj.id = :id and obj.ativo = :ativo")
	Optional<Usuarios> findByIdAndAtivo(@Param("id") Integer id, @Param("ativo") boolean ativo);
	
	
	@Query("SELECT obj FROM Usuarios obj where obj.ativo = true")
	List<Usuarios> findAllActivated();
	
	@Query("SELECT obj FROM Usuarios obj where obj.ativo = false")
	List<Usuarios> findAllDisabled();
}
