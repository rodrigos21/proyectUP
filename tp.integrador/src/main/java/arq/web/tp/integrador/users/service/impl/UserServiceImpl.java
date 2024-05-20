package arq.web.tp.integrador.users.service.impl;

import arq.web.tp.integrador.users.converter.UserConverter;
import arq.web.tp.integrador.users.dao.UserDAO;
import arq.web.tp.integrador.users.dao.repository.UserJPARepository;
import arq.web.tp.integrador.users.dto.UserDTO;
import arq.web.tp.integrador.users.entity.UserEntity;
import arq.web.tp.integrador.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private UserJPARepository userJPARepository;

    @Autowired
    public UserServiceImpl(UserDAO userDAO,
                           UserJPARepository userJPARepository) {
        this.userDAO = userDAO;
        this.userJPARepository = userJPARepository;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserEntity> entity = userJPARepository.getUsers();
        return entity.stream().map(user -> UserConverter.toDTO(user)).toList();
    }

    @Override
    public UserDTO getUser(Long userId) {
        UserEntity entity = userJPARepository.getReferenceById(userId);
        return UserConverter.toDTO(entity);
    }

    @Override
    public Long createUser(UserDTO userDTO) {
        UserEntity entity = UserConverter.toEntity(userDTO);
        var r = userJPARepository.save(entity);
        return r.getId();
    }

    @Override
    public void deleteUser(Long userId) {
        userJPARepository.deleteById(userId);
    }
}
