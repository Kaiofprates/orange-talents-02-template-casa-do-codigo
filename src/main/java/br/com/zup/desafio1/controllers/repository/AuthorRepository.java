package br.com.zup.desafio1.controllers.repository;

import br.com.zup.desafio1.models.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    Optional<Author> findByEmail(Object email);
}
