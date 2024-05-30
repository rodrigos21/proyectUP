package arq.web.tp.integrador.users.service.impl;

/*import arq.web.tp.integrador.auth.service.PasswordService;*/
import arq.web.tp.integrador.auth.dto.UserCredentialDTO;
import arq.web.tp.integrador.auth.dto.UserLoginResponse;
import arq.web.tp.integrador.exceptions.CustomException;
import arq.web.tp.integrador.users.converter.UserConverter;
import arq.web.tp.integrador.users.dao.repository.UserJPARepository;
import arq.web.tp.integrador.users.dto.UserDTO;
import arq.web.tp.integrador.users.entity.UserEntity;
import arq.web.tp.integrador.users.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private UserJPARepository userJPARepository;
    //private PasswordService passwordService;


    @Autowired
    public UserServiceImpl(UserJPARepository userJPARepository/*,
                           PasswordService passwordService*/) {
        this.userJPARepository = userJPARepository;
      /*  this.passwordService = passwordService;*/
    }

    @Override
    public List<UserDTO> getUsers() throws CustomException {
        List<UserEntity> entity = userJPARepository.getUsers();
        return entity.stream().map(user -> UserConverter.toDTO(user)).toList();
    }

    @Override
    public UserDTO getUser(Long userId) throws CustomException {
        UserEntity entity = userJPARepository.getUserById(userId);
        if (entity == null || ObjectUtils.isEmpty(entity)) {
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }
        return UserConverter.toDTO(entity);
    }

    @Override
    @Transactional
    public Long createUser(UserDTO userDTO) throws CustomException {
/*        String hashedPassword = passwordService.encodePassword(userDTO.getPassword());
        userDTO.setPassword(hashedPassword);*/
        UserEntity entity = UserConverter.toEntity(userDTO);

        try {
            var r = userJPARepository.save(entity);
            return r.getId();
        } catch (Exception e) {
            throw new CustomException("Error creating user", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void deleteUser(Long userId) throws CustomException {
        var user = userJPARepository.findById(userId);
        if (user == null || user.isEmpty()) {
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }
        userJPARepository.deleteById(userId);
    }

    @Override
    public UserLoginResponse login(UserCredentialDTO userCredentialDTO) {
        if (userCredentialDTO.getEmail() != null && userCredentialDTO.getPassword() != null) {
            UserLoginResponse response = new UserLoginResponse();
            var r = userJPARepository.findByEmail(userCredentialDTO.getEmail());
            if (r != null && userCredentialDTO.getEmail().equals(r.getEmail())
                    && userCredentialDTO.getPassword().equals(r.getPassword())) {
                UserDTO dto = new UserDTO();
                dto.setId(r.getId());
                dto.setEmail(r.getEmail());
                dto.setRole(r.getRole());
                response.setSuccess(true);
                response.setMessage("Login successfully");
                response.setUserDTO(dto);
                return response;
            }
        }
        return null;
    }
}
