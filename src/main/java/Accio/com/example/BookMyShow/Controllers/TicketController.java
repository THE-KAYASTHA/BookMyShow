package Accio.com.example.BookMyShow.Controllers;

import Accio.com.example.BookMyShow.RequestDtos.BookTicketRequest;
import Accio.com.example.BookMyShow.ResponseDtos.ShowTicketResponse;
import Accio.com.example.BookMyShow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;
        @PostMapping("/bookTicket")
    public ResponseEntity bookTicket(@RequestBody BookTicketRequest bookTicketRequest){

            try{

                String result= ticketService.bookTicket(bookTicketRequest);
                return new ResponseEntity(result,HttpStatus.OK);
            }
            catch (Exception e){

                return  new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }

        }

        @GetMapping("/viewTicket")
    public ShowTicketResponse viewTicket(@RequestParam("ticketId")Integer ticketId){

            ShowTicketResponse showTicketResponse=ticketService.viewTicket(ticketId);
            return showTicketResponse;

        }

}
