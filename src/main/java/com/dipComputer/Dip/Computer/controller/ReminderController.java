package com.dipComputer.Dip.Computer.controller;

import com.dipComputer.Dip.Computer.Dto.ReminderRequest;
import com.dipComputer.Dip.Computer.model.Reminder;
import com.dipComputer.Dip.Computer.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
@CrossOrigin(origins = "*")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @PostMapping
    public ResponseEntity<String> scheduleReminder(@RequestBody ReminderRequest request) {
        reminderService.scheduleReminder(request);
        return ResponseEntity.ok("Reminder Scheduled Successfully");
    }

    @GetMapping
    public ResponseEntity<List<Reminder>> getAllReminders() {
        List<Reminder> reminders = reminderService.getAllReminders();
        return ResponseEntity.ok(reminders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reminder> getReminderById(@PathVariable Long id) {
        Reminder reminder = reminderService.getReminderById(id);
        if (reminder != null) {
            return ResponseEntity.ok(reminder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reminder> updateReminder(@PathVariable Long id, @RequestBody ReminderRequest request) {
        Reminder updatedReminder = reminderService.updateReminder(id, request);
        if (updatedReminder != null) {
            return ResponseEntity.ok(updatedReminder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
        return ResponseEntity.noContent().build();
    }
}
