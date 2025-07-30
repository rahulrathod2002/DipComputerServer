package com.dipComputer.Dip.Computer.service;

import com.dipComputer.Dip.Computer.Dto.ReminderRequest;
import com.dipComputer.Dip.Computer.model.Reminder;
import com.dipComputer.Dip.Computer.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReminderServiceImpl implements ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    @Override
    public void scheduleReminder(ReminderRequest request) {
        Reminder reminder = new Reminder();
        reminder.setCustomerName(request.getCustomerName());
        reminder.setProduct(request.getProduct());
        reminder.setPurchaseDate(request.getPurchaseDate());
        reminder.setWhatsappNumber(request.getWhatsappNumber());
        reminder.setReminderTime(request.getReminderTime());
        reminder.setMessage(request.getMessage());
        if (request.getReminderTime() != null) {
            reminder.setNextReminderDate(LocalDate.now().plusDays(request.getReminderTime()));
        }

        reminderRepository.save(reminder);
    }

    @Override
    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    @Override
    public Reminder getReminderById(Long id) {
        Optional<Reminder> optionalReminder = reminderRepository.findById(id);
        return optionalReminder.orElse(null);
    }

    @Override
    public Reminder updateReminder(Long id, ReminderRequest request) {
        Optional<Reminder> optionalReminder = reminderRepository.findById(id);
        if (optionalReminder.isPresent()) {
            Reminder existingReminder = optionalReminder.get();
            existingReminder.setCustomerName(request.getCustomerName());
            existingReminder.setProduct(request.getProduct());
            existingReminder.setPurchaseDate(request.getPurchaseDate());
            existingReminder.setWhatsappNumber(request.getWhatsappNumber());
            existingReminder.setReminderTime(request.getReminderTime());
            existingReminder.setMessage(request.getMessage());
            existingReminder.setNextReminderDate(LocalDate.now().plusDays(request.getReminderTime()));
            return reminderRepository.save(existingReminder);
        }
        return null;
    }

    @Override
    public void deleteReminder(Long id) {
        reminderRepository.deleteById(id);
    }

    @Override
    public void sendReminders() {
        List<Reminder> reminders = reminderRepository.findByNextReminderDate(LocalDate.now());
        for (Reminder reminder : reminders) {
            // TODO: Implement WhatsApp sending logic here
            System.out.println("Sending reminder to " + reminder.getWhatsappNumber());
            // Reschedule for the next reminder
            reminder.setNextReminderDate(LocalDate.now().plusDays(reminder.getReminderTime()));
            reminderRepository.save(reminder);
        }
    }
}
