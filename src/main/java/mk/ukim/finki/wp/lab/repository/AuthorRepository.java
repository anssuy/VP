package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    List<Author> findAll();
    Optional<Author> findById(Long id);
}
