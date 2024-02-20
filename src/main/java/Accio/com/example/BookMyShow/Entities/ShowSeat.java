package Accio.com.example.BookMyShow.Entities;


import Accio.com.example.BookMyShow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="showSeats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeat {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer showSeatId;

        private  int price;
        private boolean isAvailable;
        private boolean foodAttached;

        private String seatNo; //will take reference from theaterSeat

        @Enumerated(value=EnumType.STRING)
        private SeatType seatType;

        @JoinColumn
         @ManyToOne
        private Show show;







}
