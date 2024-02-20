package Accio.com.example.BookMyShow.RequestDtos;

import Accio.com.example.BookMyShow.Enums.SeatType;
import lombok.Data;

import java.util.List;

@Data
public class BookTicketRequest {


    private int showId;
    private List<String> seatList;
    private SeatType seatType;
    private String isFoodCouponAttached;


}
