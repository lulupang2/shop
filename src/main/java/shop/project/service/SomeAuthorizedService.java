package shop.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SomeAuthorizedService {
    public void generalCanAccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(String.format("%s user accessed general service", authentication.getName()));
    }

    public void adminCanAccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(String.format("%s user accessed admin service", authentication.getName()));
    }

    public void anonymousCanAccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(String.format("%s user accessed anonymous service", authentication.getName()));
    }
}