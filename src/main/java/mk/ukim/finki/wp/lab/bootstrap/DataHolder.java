package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();

    @PostConstruct
    public void init() {
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

        books.add(new Book("Harry Potter", "Fantasy", 5.0, authors.get(0)));
        books.add(new Book("Lord of the Rings", "Fantasy", 3.2, authors.get(1)));
        books.add(new Book("1984", "Dystopian", 4.5, authors.get(2)));
        books.add(new Book("Project Hail Mary", "Science Fiction", 4.8, authors.get(3)));
        books.add(new Book("The Hitchhiker's Guide To The Galaxy", "Sci-Fi", 4.2, authors.get(4)));
        books.add(new Book("Brave New World", "Dystopian", 3.7, authors.get(5)));
        books.add(new Book("The Hobbit", "Fantasy", 2.0, authors.get(6)));
        books.add(new Book("Dune", "Science Fiction", 4.3, authors.get(7)));
        books.add(new Book("The Hunger Games", "Dystopian", 4.9, authors.get(8)));
        books.add(new Book("2001: A Space Odyssey", "Science Fiction", 4.5, authors.get(9)));
    }
}
