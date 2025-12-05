package mk.ukim.finki.wp.lab.service.impl;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private static final List<Author> authors = new ArrayList<>();

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostConstruct
    public void init() {
        if (authorRepository.findAll().isEmpty()) {
            authors.add(new Author("J.K", "Rowling", "England", "J.K Rowling biography"));
            authors.add(new Author("J.R.R", "Tolkien", "England", "J.R.R Tolkien biography"));
            authors.add(new Author("George", "Orwell", "England", "George Orwell biography"));
            authors.add(new Author("Andy", "Weir", "United States", "Andy Weir biography"));
            authors.add(new Author("Douglas", "Adams", "England", "Douglas Adams biography"));
            authors.add(new Author("Aldous", "Huxley", "England", "Aldous Huxley biography"));
            authors.add(new Author("Mary", "Shelley", "England", "Mary Shelley biography"));
            authors.add(new Author("Frank", "Herbert", "United States", "Frank Herbert biography"));
            authors.add(new Author("Suzanne", "Collins", "United States", "Suzanne Collins biography"));
            authors.add(new Author("Arthur C.", "Clarke", "England", "Arthur C. Clarke biography"));
            authorRepository.saveAll(authors);
        }
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Author not found"));
    }
}