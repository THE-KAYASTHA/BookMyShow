package Accio.com.example.BookMyShow.RequestDtos;


import Accio.com.example.BookMyShow.Entities.Movie;
import Accio.com.example.BookMyShow.Entities.Theater;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AddShowRequest {

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate showDate;
    private LocalTime showTime;



    private String movieName;



    private int theaterId;



}
