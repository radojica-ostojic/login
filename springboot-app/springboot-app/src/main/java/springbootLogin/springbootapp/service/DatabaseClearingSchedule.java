package springbootLogin.springbootapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import springbootLogin.springbootapp.repository.DatabaseClearingScheduleRepository;

@Service
@EnableScheduling
public class DatabaseClearingSchedule {
    @Autowired
    private DatabaseClearingScheduleRepository databaseClearingScheduleRepository;

    @Scheduled(cron = "0 */5 * * * *")
    void scheduleDatabaseClearing(){
        databaseClearingScheduleRepository.scheduleDatabaseConfirmationTokenClearing();
        databaseClearingScheduleRepository.scheduleDatabaseAppUserClearing();
    }

}
