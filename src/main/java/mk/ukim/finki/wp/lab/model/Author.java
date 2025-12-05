package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String country;
    private String biography;
    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<>();

    public Author (String name, String surname, String country, String biography) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.biography = biography;
    }
}