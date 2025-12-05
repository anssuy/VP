package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getTitle().contains(text) && book.getAverageRating() >= rating)
                .toList();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
    }

    @Override
    public Book save(String title, String genre, Double averageRating, Long authorId) {
        Book book = new Book(title, genre, averageRating, authorService.findById(authorId));
        return bookRepository.save(book);
    }

    @Override
    public Book edit(Long id, String title, String genre, Double averageRating, Long authorId) {
        Book book = findById(id);
        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(authorService.findById(authorId));
        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}