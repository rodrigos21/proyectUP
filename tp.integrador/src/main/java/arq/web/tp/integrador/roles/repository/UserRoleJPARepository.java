package arq.web.tp.integrador.roles.repository;

import arq.web.tp.integrador.roles.dto.UserRoleId;
import arq.web.tp.integrador.roles.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRoleJPARepository extends JpaRepository<UserRoleEntity, UserRoleId> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value =" INSERT INTO USER_ROLES (USER_ID,ROLE_ID) VALUES (:userId, :roleId) ")
    void addRoleToUser(Long userId, Long roleId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = " DELETE FROM USER_ROLES WHERE USER_ID=:userId AND ROLE_ID=:roleId ")
    void deleteUserRole(Long userId, Long roleId);

    @Transactional
    void deleteByUserId(Long userId);


    @Transactional
    @Query(nativeQuery = true, value = "SELECT * FROM USER_ROLES where ROLE_ID =:roleId ")
    List<UserRoleEntity> getUsersRole(Long roleId);
}
