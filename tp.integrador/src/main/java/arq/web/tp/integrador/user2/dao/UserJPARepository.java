package arq.web.tp.integrador.user2.dao;

import arq.web.tp.integrador.user2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJPARepository extends JpaRepository<UserEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM USERS")
    List<UserEntity> getUsers();

    @Query(nativeQuery = true, value = "SELECT * FROM USERS WHERE id = :userId")
    UserEntity getUserById(@Param("userId") Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM USERS WHERE EMAIL = :email")
    UserEntity findByEmail(@Param("email") String email);

}
