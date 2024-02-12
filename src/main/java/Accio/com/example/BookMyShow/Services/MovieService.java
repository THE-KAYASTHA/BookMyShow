package Accio.com.example.BookMyShow.Services;

import Accio.com.example.BookMyShow.Entities.Movie;
import Accio.com.example.BookMyShow.Repositories.MovieRepository;
import Accio.com.example.BookMyShow.RequestDtos.AddMovieRequest;
import Accio.com.example.BookMyShow.TransFormers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(AddMovieRequest addMovieRequest){

        Movie movie= MovieTransformer.convertDtoRequestToEntity(addMovieRequest);
            movie=movieRepository.save(movie);

            return "The movie has been saved with the movieId "+movie.getMovieId();
    }

}
