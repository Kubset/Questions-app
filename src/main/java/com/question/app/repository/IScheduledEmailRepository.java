package com.question.app.repository;

import com.question.app.model.ScheduledEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IScheduledEmailRepository extends JpaRepository<ScheduledEmail, Long> {

    @Transactional
    List<ScheduledEmail> findAll();
}
