package shop.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import shop.project.domain.Users;
import shop.project.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;


    public List<Users> userAll() throws NullPointerException  {
        return userRepository.findAll();
    }

    public Long save(UserDTO userDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));

        return userRepository.save(Users.builder().
                username(userDTO.getUsername()).
                password(userDTO.getPassword()).
                useremail(userDTO.getUseremail()).
                usernick(userDTO.getUsernick()).
                role(userDTO.getRole()).
                addr1(userDTO.getAddr1()).
                addr2(userDTO.getAddr2()).
                addr3(userDTO.getAddr3()).
                build()).getId();
    }

    @Override
    public Users loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

}