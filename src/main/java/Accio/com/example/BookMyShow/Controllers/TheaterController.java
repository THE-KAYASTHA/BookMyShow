package Accio.com.example.BookMyShow.Controllers;


import Accio.com.example.BookMyShow.RequestDtos.AddTheaterRequest;
import Accio.com.example.BookMyShow.RequestDtos.AddTheaterSeatsRequest;
import Accio.com.example.BookMyShow.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {



    @Autowired
    private TheaterService theaterService;
    @PostMapping("/addTheater")
    public ResponseEntity addTheater(@RequestBody AddTheaterRequest addTheaterRequest){
    String result=theaterService.addTheater(addTheaterRequest);

    return new ResponseEntity(result, HttpStatus.OK);


    }

    @PostMapping("/addTheaterSeats")
    public String addTheaterSeats(@RequestBody AddTheaterSeatsRequest addTheaterSeatsRequest){

        String result =theaterService.addTheaterSeats(addTheaterSeatsRequest);
        return result;

    }
}
