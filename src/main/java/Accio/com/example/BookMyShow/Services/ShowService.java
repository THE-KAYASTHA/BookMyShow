package Accio.com.example.BookMyShow.Services;


import Accio.com.example.BookMyShow.CustomException.InvalidMovieException;
import Accio.com.example.BookMyShow.CustomException.InvalidTheaterException;
import Accio.com.example.BookMyShow.Entities.*;
import Accio.com.example.BookMyShow.Enums.SeatType;
import Accio.com.example.BookMyShow.Repositories.MovieRepository;
import Accio.com.example.BookMyShow.Repositories.ShowRespository;
import Accio.com.example.BookMyShow.Repositories.ShowSeatRepository;
import Accio.com.example.BookMyShow.Repositories.TheaterRepository;
import Accio.com.example.BookMyShow.RequestDtos.AddShowRequest;
import Accio.com.example.BookMyShow.RequestDtos.AddShowSeatsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRespository showRespository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

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
                                .showTime(showRequest.getShowTime()).build();
                showEntity.setMovie(movie);
                showEntity.setTheater(theater);
                movie.getShowList().add(showEntity);
                theater.getShowList().add(showEntity);
                showEntity=showRespository.save(showEntity);
                return "The show has been created with the showId "+showEntity.getShowId();

            }

            public String addShowSeats(AddShowSeatsRequest addShowSeatsRequest){

            Show show=showRespository.findById(addShowSeatsRequest.getShowId()).get();

            Theater theater=show.getTheater();
                List<TheaterSeat> theaterSeatList=theater.getTheaterSeatList();
                List<ShowSeat> showSeatList=new ArrayList<>();

                for(TheaterSeat theaterSeat:theaterSeatList){

                    String seatNo=theaterSeat.getSeatNo();
                    SeatType seatType=theaterSeat.getSeatType();

                    ShowSeat showSeat=ShowSeat.builder().seatNo(seatNo)
                            .seatType(seatType)
                            .foodAttached(false)
                            .isAvailable(true)
                            .show(show).build();

                    if(seatType.equals(SeatType.CLASSIC)){
                        showSeat.setPrice(addShowSeatsRequest.getPriceForClassicSeats());
                    }
                    else{
                        showSeat.setPrice(addShowSeatsRequest.getPriceForPremiumSeats());
                    }

                    showSeatList.add(showSeat);
                }


                    showSeatRepository.saveAll(showSeatList);
                return "Show seats have been added to the System";

            }
}
