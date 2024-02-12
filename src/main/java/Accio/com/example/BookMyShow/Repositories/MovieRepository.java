package Accio.com.example.BookMyShow.Repositories;

import Accio.com.example.BookMyShow.Entities.Movie;
import Accio.com.example.BookMyShow.Enums.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Integer> {


    Movie findMovieByMovieNameAndMovieLanguage(String movieName, Language language);

    Movie findMovieByMovieName(String movieName);


    List<Movie> findAllByDurationGreaterThanEqual(double duration);

}
