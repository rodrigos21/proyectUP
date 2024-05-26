package arq.web.tp.integrador.users.dao.repository;

import arq.web.tp.integrador.exceptions.CustomException;
import arq.web.tp.integrador.users.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJPARepository extends JpaRepository<UserEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM USERS")
    List<UserEntity> getUsers() throws CustomException;

    @Query(nativeQuery = true, value = "SELECT * FROM USERS WHERE ID_USER = :userId")
    UserEntity getUserById(@Param("userId")Long userId) throws CustomException;
}
