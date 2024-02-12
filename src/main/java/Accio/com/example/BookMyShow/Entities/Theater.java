package Accio.com.example.BookMyShow.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="theaters")
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterId;
    private String name;
    private String address;
    private Integer noOfScreens;



    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<TheaterSeat> theaterSeatList=new ArrayList<>();


    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<Show> showList=new ArrayList<>();

}
