package arq.web.tp.integrador.roles.repository;


import arq.web.tp.integrador.roles.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleJPARepository extends JpaRepository<RoleEntity, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE ROLES set name =:name, description =:description WHERE id =:id ")
    void updateRole(Long id, String name, String description);

}
