package Accio.com.example.BookMyShow.Services;


import Accio.com.example.BookMyShow.Entities.Show;
import Accio.com.example.BookMyShow.Entities.ShowSeat;
import Accio.com.example.BookMyShow.Entities.TheaterSeat;
import Accio.com.example.BookMyShow.Entities.Ticket;
import Accio.com.example.BookMyShow.Repositories.ShowRespository;
import Accio.com.example.BookMyShow.Repositories.ShowSeatRepository;
import Accio.com.example.BookMyShow.Repositories.TicketRepository;
import Accio.com.example.BookMyShow.RequestDtos.BookTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ShowRespository showRespository;
    @Autowired
    private ShowSeatRepository showSeatRepository;

    public String bookTicket(BookTicketRequest bookTicketRequest)throws Exception{

         Show show=showRespository.findById(bookTicketRequest.getShowId()).get();
        List<ShowSeat> showSeatList=show.getShowSeatList();
        int totalBookingAmount=0;

        for(String seatToBeBooked : bookTicketRequest.getSeatList()){
            if(!showSeatList.contains(showSeatRepository.findShowSeatBySeatNoAndSeatType(seatToBeBooked,bookTicketRequest.getSeatType()))){
                throw new Exception("Seat No "+seatToBeBooked+" does not exist in the theaterSeat.");
            }

            for(ShowSeat showSeat: showSeatList){

                if(showSeat.getSeatNo().equals(seatToBeBooked) &&(bookTicketRequest.getSeatType().equals(showSeat.getSeatType()))){

                        if(showSeat.isAvailable()){
                            showSeat.setAvailable(Boolean.FALSE);
                            totalBookingAmount+= showSeat.getPrice();
                        }
                        else{
                            throw new Exception("Seat No "+showSeat.getSeatNo()+" is already Booked.");
                        }

                        if(bookTicketRequest.getIsFoodCouponAttached().equals("true")){
                            showSeat.setFoodAttached(Boolean.TRUE);
                            totalBookingAmount+=100;
                        }

                }


            }
        }

        Ticket ticket=Ticket.builder()
                .seatNosBooked(bookTicketRequest.getSeatList().toString())
                .totalAmountPaid(totalBookingAmount)

                .show(show)
                .build();

        if(bookTicketRequest.getIsFoodCouponAttached().equals("true")){
            ticket.setFoodAttached(Boolean.TRUE);
        }
        else{
            ticket.setFoodAttached(Boolean.FALSE);
        }
            show.getTicketList().add(ticket);
            ticket=ticketRepository.save(ticket);
            return "This is the ticket with the ticketId "+ticket.getTicketId();
    }

}
