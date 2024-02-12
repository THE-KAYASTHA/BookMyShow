package Accio.com.example.BookMyShow.TransFormers;

import Accio.com.example.BookMyShow.Entities.Movie;
import Accio.com.example.BookMyShow.RequestDtos.AddMovieRequest;

public class MovieTransformer {


    public static Movie convertDtoRequestToEntity(AddMovieRequest addMovieRequest){
        Movie movie= Movie.builder().movieLanguage(addMovieRequest.getMovieLanguage())
                .movieName(addMovieRequest.getMovieName())
                .genre(addMovieRequest.getGenre())
                .duration(addMovieRequest.getDuration())
                .releaseDate(addMovieRequest.getReleaseDate())
                .rating(addMovieRequest.getRating())
                .build();

        return movie;

    }
}
