package Accio.com.example.BookMyShow.TransFormers;

import Accio.com.example.BookMyShow.Entities.Theater;
import Accio.com.example.BookMyShow.RequestDtos.AddTheaterRequest;

public class TheaterTransformer {


    public static Theater convertDtoRequestToEntity(AddTheaterRequest addTheaterRequest){


        Theater theater=Theater.builder()
                .name(addTheaterRequest.getName())
                .address(addTheaterRequest.getAddress())
                .noOfScreens(addTheaterRequest.getNoOfScreens())
                .build();

        return theater;
    }
}
