package org.generation.BlogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.generation.BlogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	public List<Postagem> findAllByTituloContainingIgnoreCase(String title);
}
