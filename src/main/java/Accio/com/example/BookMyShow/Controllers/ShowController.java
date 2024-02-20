package Accio.com.example.BookMyShow.Controllers;


import Accio.com.example.BookMyShow.RequestDtos.AddShowRequest;
import Accio.com.example.BookMyShow.RequestDtos.AddShowSeatsRequest;
import Accio.com.example.BookMyShow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shows")
public class ShowController {


    @Autowired
    private ShowService showService;
    @PostMapping("/addShow")
    public ResponseEntity addShows(@RequestBody AddShowRequest addShowRequest){

        try {
            String result = showService.addShow(addShowRequest);
            return new ResponseEntity(result, HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addShowSeats")
    public ResponseEntity addShowSeats(@RequestBody AddShowSeatsRequest addShowSeatsRequest){
        try {
                String result = showService.addShowSeats(addShowSeatsRequest);
                return new ResponseEntity(result, HttpStatus.OK);
        }
        catch (Exception e){

            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
