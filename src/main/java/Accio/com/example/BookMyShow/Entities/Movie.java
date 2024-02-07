package Accio.com.example.BookMyShow.Entities;


import Accio.com.example.BookMyShow.Enums.Genre;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="movies")
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String movieId;
    @Column(unique = true,nullable = false)
    private String movieName;

    private Genre genre;
}
