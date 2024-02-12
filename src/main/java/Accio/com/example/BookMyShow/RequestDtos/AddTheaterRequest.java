package Accio.com.example.BookMyShow.RequestDtos;


import lombok.Data;

@Data
public class AddTheaterRequest {


    private String name;
    private String address;
    private Integer noOfScreens;
}
