package Accio.com.example.BookMyShow.Services;


import Accio.com.example.BookMyShow.Entities.*;
import Accio.com.example.BookMyShow.Repositories.ShowRespository;
import Accio.com.example.BookMyShow.Repositories.ShowSeatRepository;
import Accio.com.example.BookMyShow.Repositories.TicketRepository;
import Accio.com.example.BookMyShow.Repositories.UserRepository;
import Accio.com.example.BookMyShow.RequestDtos.BookTicketRequest;
import Accio.com.example.BookMyShow.ResponseDtos.ShowTicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {


    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ShowRespository showRespository;
    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private UserRepository userRepository;

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

        User user=userRepository.findByEmailId(bookTicketRequest.getEmailId());


        Ticket ticket=Ticket.builder()
                .seatNosBooked(bookTicketRequest.getSeatList().toString())
                .totalAmountPaid(totalBookingAmount)
                .user(user)
                .show(show)
                .build();


        if(bookTicketRequest.getIsFoodCouponAttached().equals("true")){
            ticket.setFoodAttached(Boolean.TRUE);
        }
        else{
            ticket.setFoodAttached(Boolean.FALSE);
        }
        user.getTicketList().add(ticket);
            show.getTicketList().add(ticket);
            ticket=ticketRepository.save(ticket);
            return "This is the ticket with the ticketId "+ticket.getTicketId();
    }

    public ShowTicketResponse viewTicket(Integer ticketId){

        Ticket ticket=ticketRepository.findById(ticketId).get();
        Show show=ticket.getShow();
        String movieName=show.getMovie().getMovieName();
        String theaterInfo=show.getTheater().getName()+" "+show.getTheater().getAddress();
        String bookedSeats=ticket.getSeatNosBooked();

        ShowTicketResponse showTicketResponse=ShowTicketResponse.builder()
                .movieName(movieName)
                .theaterInfo(theaterInfo)
                .showDate(show.getShowDate())
                .showTime(show.getShowTime())
                .seatNos(bookedSeats)
                .totalAmount(ticket.getTotalAmountPaid())
                .build();

        String emailId=ticket.getUser().getEmailId();

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("kulshreshthakamalwings@gmail.com");
        simpleMailMessage.setTo(emailId);
        simpleMailMessage.setSubject("Movie Ticket Confirmation");
        simpleMailMessage.setText(showTicketResponse.toString());

        javaMailSender.send(simpleMailMessage);

        return showTicketResponse;


    }

}
