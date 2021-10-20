package shop.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.project.domain.Users;
import shop.project.service.UserDTO;
import shop.project.service.UserService;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class UsersController {
    @Autowired
    private UserService userService;

    //유저 목록 출력
    @GetMapping("/list")
    public List<Users> userlist() {
        return userService.userAll();
    }
    //회원 가입 post요청
    @PostMapping("signup")
    public UserDTO signup(@RequestBody UserDTO userDTO){
        userService.save(userDTO);
        return userDTO;
    }


}
