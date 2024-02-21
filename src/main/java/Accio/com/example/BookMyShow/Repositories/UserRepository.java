package Accio.com.example.BookMyShow.Repositories;

import Accio.com.example.BookMyShow.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmailId(String emailId);
}
