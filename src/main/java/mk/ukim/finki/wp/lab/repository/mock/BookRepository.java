package mk.ukim.finki.wp.lab.repository.mock;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);
    Optional<Book> findById(Long id);
    Book save(Book book);
    void delete(Long id);
}
