package paopao.demo.service;

import org.springframework.stereotype.Service;
import paopao.demo.entity.User;

import java.util.Optional;

@Service
public interface UserService {
    boolean existsByUsername(String username);
    void register(User user);
    User findByUsername(String username);
}
