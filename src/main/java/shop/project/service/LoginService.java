package shop.project.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
public interface LoginService {
    public String loginCheck(HashMap<String, String> hashParam);
}
