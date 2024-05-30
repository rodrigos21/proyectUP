/*
package arq.web.tp.integrador.auth.service;

import arq.web.tp.integrador.users.dao.repository.UserJPARepository;
import arq.web.tp.integrador.users.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserJPARepository userRepository;
    private final PasswordService passwordService;

    @Autowired
    public AuthenticationService(UserJPARepository userRepository,
                                 PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public boolean authenticate(String email, String rawPassword) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            return passwordService.matches(rawPassword, user.getPassword());
        }
        return false;
    }
}
*/

