package shop.project.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import shop.project.domain.Users;

import java.util.List;

public class UserAccount extends User {
    private Users users;
    public UserAccount(Users users){
        super(users.getUsername(), users.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_"+users.getRole())));
        this.users=users;
    }
}