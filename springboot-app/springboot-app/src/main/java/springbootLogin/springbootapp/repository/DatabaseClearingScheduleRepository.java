package springbootLogin.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springbootLogin.springbootapp.entity.ConfirmationToken;

@Repository
public interface DatabaseClearingScheduleRepository extends JpaRepository<ConfirmationToken, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ConfirmationToken WHERE CURRENT_TIMESTAMP > expiresAt AND confirmedAt IS NULL")
    void scheduleDatabaseConfirmationTokenClearing();

    @Transactional
    @Modifying
    @Query("DELETE FROM AppUser WHERE id NOT IN(SELECT c.appUser FROM ConfirmationToken c)")
    void scheduleDatabaseAppUserClearing();
}
