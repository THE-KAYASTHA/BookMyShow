package Accio.com.example.BookMyShow.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="tickets")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    private String seatNosBooked;
    private LocalTime showTime;
    private LocalDate showDate;
    private String movieName;
    private String theaterNameAndAdd;
    private boolean foodAttached;
    private Integer totalAmountPaid;

    //seatType
    @JoinColumn
    @ManyToOne
    private Show show;


    @JoinColumn
    @ManyToOne
    private User user;
}
