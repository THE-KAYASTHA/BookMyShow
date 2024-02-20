package Accio.com.example.BookMyShow.Repositories;

import Accio.com.example.BookMyShow.Entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRespository extends JpaRepository<Show,Integer> {


}
