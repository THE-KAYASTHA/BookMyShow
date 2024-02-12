package Accio.com.example.BookMyShow.Entities;


import Accio.com.example.BookMyShow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="theaterSeats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TheaterSeat {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterSeatId;

    private String seatNo;


    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;


    @JoinColumn
    @ManyToOne
    private Theater theater;

}
