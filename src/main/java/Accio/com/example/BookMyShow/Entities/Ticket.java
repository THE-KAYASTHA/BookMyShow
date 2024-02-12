package Accio.com.example.BookMyShow.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tickets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    private String seatNosBooked;

    private Integer totalAmountPaid;


    @JoinColumn
    @ManyToOne
    private Show show;
}
