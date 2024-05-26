package arq.web.tp.integrador.users.service.impl;

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

    @Autowired
    public UserServiceImpl(UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
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
}
