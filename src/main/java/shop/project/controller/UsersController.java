package shop.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import shop.project.domain.Users;
import shop.project.jwt.JwtAuthenticationProvider;
import shop.project.repository.UserRepository;
import shop.project.service.UserDTO;
import shop.project.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UsersController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    //유저 목록 출력
    @GetMapping("/list")
    public List<Users> userlist() {
        return userService.userAll();
    }

    //회원 가입 post요청
    @PostMapping("signup")
    public void signup(@RequestBody UserDTO user) {
        userRepository.save(Users.builder()
                .username(user.getUsername())
                .useremail(user.getUseremail())
                .password(passwordEncoder.encode(user.getPassword()))
                .addr1(user.getAddr1())
                .addr2(user.getAddr2())
                .addr3(user.getAddr3())
                .build());

    }
    @PostMapping("/login")
    public String login(@RequestBody UserDTO user, HttpServletResponse response) {
        Users member = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다."));
        if (!passwordEncoder.matches(user.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        return jwtAuthenticationProvider.createToken(member.getUsername(), member.getRole());
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response){
        Cookie cookie = new Cookie("X-AUTH-TOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
//
//    @GetMapping("/info")
//    public UserDTO getInfo(){
//        Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if(details != null && !(details instanceof  String)) return new UserDTO((Users) details);
//        return null;
//    }

}
