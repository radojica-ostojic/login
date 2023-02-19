package springbootLogin.springbootapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DatabaseClearingScheduleRepository {

    @Transactional
    @Query(

    )
    void scheduleDatabaseClearing();

}
