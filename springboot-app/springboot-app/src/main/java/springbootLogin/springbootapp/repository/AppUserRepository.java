package springbootLogin.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springbootLogin.springbootapp.entity.AppUser;
import springbootLogin.springbootapp.entity.AppUserRole;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository
        extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(@Param("email") String email);


    //      TODO: if email not confirmed send confirmation email
    @Transactional
    @Query( "select count(c.id) " +
            "from ConfirmationToken c " +
            "inner join AppUser a " +
            "on c.id = a.id " +
            "where c.confirmedAt is null " +
            "and c.expiresAt > CURRENT_TIMESTAMP " +
            "and a.email = ?1")
    int emailExists(@Param("email") String email);

    @Transactional
    @Query( "SELECT appUserRole FROM AppUser WHERE email = ?1")
    String fetchAppUserRole(@Param("email") String email);
}
