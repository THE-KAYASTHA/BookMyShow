package Accio.com.example.BookMyShow.Repositories;

import Accio.com.example.BookMyShow.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmailId(String emailId);
}
