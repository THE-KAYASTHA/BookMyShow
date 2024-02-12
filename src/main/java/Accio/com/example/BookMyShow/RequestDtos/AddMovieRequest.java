package Accio.com.example.BookMyShow.RequestDtos;

import Accio.com.example.BookMyShow.Enums.Genre;
import Accio.com.example.BookMyShow.Enums.Language;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AddMovieRequest {




    private String movieName;


    private Genre genre;


    private Language movieLanguage;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;

    private double duration;
    private double rating;

}
