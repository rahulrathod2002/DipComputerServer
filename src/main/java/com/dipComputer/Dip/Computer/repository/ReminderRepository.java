package com.dipComputer.Dip.Computer.repository;

import com.dipComputer.Dip.Computer.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findByNextReminderDate(LocalDate date);
}
