package Accio.com.example.BookMyShow.Repositories;

import Accio.com.example.BookMyShow.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
