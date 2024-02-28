package Accio.com.example.BookMyShow.Services;


import Accio.com.example.BookMyShow.Entities.Theater;
import Accio.com.example.BookMyShow.Entities.TheaterSeat;
import Accio.com.example.BookMyShow.Enums.SeatType;
import Accio.com.example.BookMyShow.Repositories.TheaterRepository;
import Accio.com.example.BookMyShow.RequestDtos.AddTheaterRequest;
import Accio.com.example.BookMyShow.RequestDtos.AddTheaterSeatsRequest;
import Accio.com.example.BookMyShow.TransFormers.TheaterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

@Autowired
    private TheaterRepository theaterRepository;


public String addTheater(AddTheaterRequest addTheaterRequest){

        Theater theater= TheaterTransformer.convertDtoRequestToEntity(addTheaterRequest);
        theater=theaterRepository.save(theater);

    return "New theater is added with theaterId "+theater.getTheaterId();
}
    public void addseatHelper(Integer noOfSeat, SeatType seatType, Integer col, List<TheaterSeat>theaterSeatList, Theater theater)
    {
        int div=noOfSeat/col;
        int rem=noOfSeat%col;
        for(char ch='A';ch<'A'+div;ch++ )
        {
            for(int i=1;i<=col;i++)
            {
                String seatno= ch+""+i+""+ seatType.toString().charAt(0);
                TheaterSeat tSeat= TheaterSeat.builder()
                        .seatNo(seatno)
                        .seatType(seatType)
                        .theater(theater) //set  parent  in child class
                        .build();
                theaterSeatList.add(tSeat);
            }
        }
        char chR= (char) ('A'+div);
        for(int  i=1;i<=rem;i++)
        {
            String seatno= chR+""+i+""+ seatType.toString().charAt(0);
            TheaterSeat tSeat= TheaterSeat.builder()
                    .seatNo(seatno)
                    .seatType(seatType)
                    .theater(theater)
                    .build();
            theaterSeatList.add(tSeat);
        }
    }
public String addTheaterSeats(AddTheaterSeatsRequest theaterSeatsRequest){
    List<TheaterSeat> theaterSeatList=new ArrayList<>();
    int col=theaterSeatsRequest.getNoOfCol();
    int noOfPremium=theaterSeatsRequest.getNoOfPremiumSeats();
    int noOfClassic=theaterSeatsRequest.getNoOfClassicSeats();
    SeatType Pseattype=SeatType.PREMIUM;
    SeatType CseatType=SeatType.CLASSIC;
    Theater theater= theaterRepository.findById(theaterSeatsRequest.getTheaterId()).get();
    addseatHelper(noOfPremium,Pseattype,col,theaterSeatList,theater);
    addseatHelper(noOfClassic,CseatType,col,theaterSeatList,theater);
    //set child class attibute in parent  class
    theater.setTheaterSeatList(theaterSeatList);
    theaterRepository.save(theater);
    return "Theater Seats Added Succesfully";


}

}
