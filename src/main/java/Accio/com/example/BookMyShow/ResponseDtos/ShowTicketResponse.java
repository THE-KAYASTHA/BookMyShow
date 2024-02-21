package Accio.com.example.BookMyShow.ResponseDtos;

import Accio.com.example.BookMyShow.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowTicketResponse {

    private String movieName;
    private String theaterInfo;
    private LocalDate showDate;
    private LocalTime showTime;

    private int totalAmount;
    private String seatNos;
    private SeatType seatType;
}
