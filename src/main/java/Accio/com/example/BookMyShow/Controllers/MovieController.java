package Accio.com.example.BookMyShow.Controllers;


import Accio.com.example.BookMyShow.Entities.Movie;
import Accio.com.example.BookMyShow.RequestDtos.AddMovieRequest;
import Accio.com.example.BookMyShow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {


    @Autowired
    private MovieService movieService;
    @PostMapping("/addMovie")
    public ResponseEntity addMovie(@RequestBody AddMovieRequest addMovieRequest){

        String result= movieService.addMovie(addMovieRequest);
        return new ResponseEntity(result, HttpStatus.OK);


    }


}
