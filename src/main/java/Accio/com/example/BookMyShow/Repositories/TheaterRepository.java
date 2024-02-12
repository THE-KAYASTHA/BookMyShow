package Accio.com.example.BookMyShow.Repositories;

import Accio.com.example.BookMyShow.Entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Integer> {
}
