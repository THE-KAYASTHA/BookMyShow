package Accio.com.example.BookMyShow.Services;


import Accio.com.example.BookMyShow.CustomException.InvalidMovieException;
import Accio.com.example.BookMyShow.CustomException.InvalidTheaterException;
import Accio.com.example.BookMyShow.Entities.Movie;
import Accio.com.example.BookMyShow.Entities.Show;
import Accio.com.example.BookMyShow.Entities.Theater;
import Accio.com.example.BookMyShow.Repositories.MovieRepository;
import Accio.com.example.BookMyShow.Repositories.ShowRespository;
import Accio.com.example.BookMyShow.Repositories.TheaterRepository;
import Accio.com.example.BookMyShow.RequestDtos.AddShowRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRespository showRespository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MovieRepository movieRepository;


    public String addShow(AddShowRequest showRequest) throws Exception{

                Movie movie=movieRepository.findMovieByMovieName(showRequest.getMovieName());
                if(movie==null){
                    throw new InvalidMovieException("Movie name enter does not exist in the DB");

                }

                Optional<Theater> theaterOptional=theaterRepository.findById(showRequest.getTheaterId());

                if(theaterOptional.isEmpty()){

                    throw new InvalidTheaterException("TheaterId entered is incorrect");
                }

                Theater theater=theaterOptional.get();

                Show showEntity=Show.builder().showDate(showRequest.getShowDate())
                                .showTime(showRequest.getShowTime())
                                .movie(movie)
                                .theater(theater).build();

                movie.getShowList().add(showEntity);
                theater.getShowList().add(showEntity);
                showEntity=showRespository.save(showEntity);
                return "The show has been created with the showId "+showEntity.getShowId();

            }
}
