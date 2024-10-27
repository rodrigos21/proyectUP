package arq.web.tp.integrador.user.dao;

import arq.web.tp.integrador.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJPARepository extends JpaRepository<UserEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM users")
    List<UserEntity> getUsers();

    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE id = :userId")
    UserEntity getUserById(@Param("userId") Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE EMAIL = :email")
    UserEntity findByEmail(@Param("email") String email);

}
