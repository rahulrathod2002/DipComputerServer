package com.dipComputer.Dip.Computer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    private ReminderService reminderService;

    // a cron job to run every day at 10:00 AM
    @Scheduled(cron = "0 0 10 * * ?")
    public void sendScheduledReminders() {
        reminderService.sendReminders();
    }
}
