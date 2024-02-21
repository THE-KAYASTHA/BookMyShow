package Accio.com.example.BookMyShow.Controllers;

import Accio.com.example.BookMyShow.RequestDtos.AddUserRequest;
import Accio.com.example.BookMyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/addUser")
    private String addUser(@RequestBody AddUserRequest addUserRequest){

        String result=userService.addUser(addUserRequest);
        return result;
    }



}
