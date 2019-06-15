package com.question.app.repository;

import com.question.app.model.ScheduledEmails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IScheduledEmailsRepository extends JpaRepository<ScheduledEmails, Long> {

    @Transactional
    List<ScheduledEmails> findAll();
}
