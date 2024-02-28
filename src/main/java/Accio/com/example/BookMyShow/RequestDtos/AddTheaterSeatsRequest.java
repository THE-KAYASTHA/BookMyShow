package Accio.com.example.BookMyShow.RequestDtos;


import lombok.Data;

@Data
public class AddTheaterSeatsRequest {

private int noOfClassicSeats;
private int theaterId;
private int noOfPremiumSeats;

    private Integer noOfCol;
}
