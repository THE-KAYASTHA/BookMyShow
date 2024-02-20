package Accio.com.example.BookMyShow.Repositories;

import Accio.com.example.BookMyShow.Entities.ShowSeat;
import Accio.com.example.BookMyShow.Enums.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Integer> {

    ShowSeat findShowSeatBySeatNoAndSeatType(String seatNo, SeatType seatType);
}
