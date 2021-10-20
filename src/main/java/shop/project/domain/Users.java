package shop.project.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String useremail;
    private String usernick;
    private String role;
    private String addr1;
    private String addr2;
    private String addr3;
    @CreationTimestamp
    private LocalDateTime createdate;

    @Builder
    public Users(String username,
                 String password,
                 String useremail,
                 String usernick,
                 String role,
                 String addr1,
                 String addr2,
                 String addr3) {
        this.username = username;
        this.password = password;
        this.useremail = useremail;
        this.usernick = usernick;
        this.role = role;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.addr3 = addr3;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
//        for (String role : role.split(",")) {
//            roles.add(new SimpleGrantedAuthority(role));
//        }
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
