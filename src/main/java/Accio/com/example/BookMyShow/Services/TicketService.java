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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        Optional<Show> optionalShow=showRespository.findById(bookTicketRequest.getShowId());
        if(optionalShow.isEmpty())
        {
            throw new Exception("Show not available for given movie at given time in given Theater Try changing  time OR Movie OR Theater");
        }
        Show show=optionalShow.get();
        Optional<User> optionalUser=userRepository.findByEmailId(bookTicketRequest.getEmailId());
        if(optionalUser.isEmpty())
        {
            throw new Exception("Email does not Exist");
        }

        //assign seats to  ticket
        List<ShowSeat>showSeatList=show.getShowSeatList();
        int totalAmt=0;
        //List<showSeat>bookedSeatAgainstTicket=new ArrayList<>();
        List<String> seatNumbers=bookTicketRequest.getSeatList();
        for(ShowSeat seat:showSeatList)
        {
            if(seatNumbers.contains(seat.getSeatNo()) && seat.isAvailable()==Boolean.TRUE)
            {
                seat.setAvailable(Boolean.FALSE);
                //bookedSeatAgainstTicket.add(seat);
                seat.setFoodAttached(bookTicketRequest.isFoodCouponAttached());
                totalAmt+=seat.getPrice();
            }
            else if(seatNumbers.contains(seat.getSeatNo()) && seat.isAvailable()==Boolean.FALSE){
                throw new Exception("Seat No "+seat.getSeatNo()+" is already booked.");
            }
        }
        if(bookTicketRequest.isFoodCouponAttached() )
        {
            totalAmt+=150* seatNumbers.size();
        }
        User user=optionalUser.get();


        Ticket ticket=Ticket.builder()
                .totalAmountPaid(totalAmt)
                .movieName(show.getMovie().getMovieName())
                .theaterNameAndAdd(show.getTheater().getName()+" "+show.getTheater().getAddress())
                .foodAttached(bookTicketRequest.isFoodCouponAttached())
                .seatNosBooked(bookTicketRequest.getSeatList().toString())
                .showTime(show.getShowTime())
                .showDate(show.getShowDate())
                .show(show)
                .user(user)
                .build();


        user.getTicketList().add(ticket);
            show.getTicketList().add(ticket);
            ticket=ticketRepository.save(ticket);
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("kulshreshthakamalwings@gmail.com");
        simpleMailMessage.setTo(bookTicketRequest.getEmailId());
        simpleMailMessage.setSubject("Movie Ticket Confirmation");
        simpleMailMessage.setText("Hey "+user.getName()+" Your tickets book Successfully for "+ticket.getMovieName()+" at "+
                ticket.getTheaterNameAndAdd()+". Your seats  are "+ticket.getSeatNosBooked()+" Reach 10-20 Minutes prior to show. Your show Time is "+
                ticket.getShowTime()+" "+ticket.getShowDate()+". And  Amount  of Rs "+ticket.getTotalAmountPaid()+" is already Paid");

        javaMailSender.send(simpleMailMessage);
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
        simpleMailMessage.setText("Hey "+ticket.getUser().getName()+" Your tickets book Successfully for "+ticket.getMovieName()+" at "+
                ticket.getTheaterNameAndAdd()+". Your seats  are "+ticket.getSeatNosBooked()+" Reach 10-20 Minutes prior to show. Your show Time is "+
                ticket.getShowTime()+" "+ticket.getShowDate()+". And  Amount  of Rs "+ticket.getTotalAmountPaid()+" is already Paid");

        javaMailSender.send(simpleMailMessage);

        return showTicketResponse;


    }

}
