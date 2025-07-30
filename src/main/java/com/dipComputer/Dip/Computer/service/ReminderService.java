package com.dipComputer.Dip.Computer.service;

import com.dipComputer.Dip.Computer.Dto.ReminderRequest;
import com.dipComputer.Dip.Computer.model.Reminder;

import java.util.List;

public interface ReminderService {
    void scheduleReminder(ReminderRequest request);
    List<Reminder> getAllReminders();
    Reminder getReminderById(Long id);
    Reminder updateReminder(Long id, ReminderRequest request);
    void deleteReminder(Long id);
    void sendReminders();
}
