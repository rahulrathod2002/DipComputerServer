
package com.dipComputer.Dip.Computer.controller;

import com.dipComputer.Dip.Computer.Dto.ReminderRequest;
import com.dipComputer.Dip.Computer.model.Reminder;
import com.dipComputer.Dip.Computer.service.ReminderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")

public class ReminderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReminderService reminderService;

    @InjectMocks
    private ReminderController reminderController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reminderController).build();
        objectMapper.findAndRegisterModules();
    }

    @Test
    public void testGetAllReminders() throws Exception {
        Reminder reminder1 = new Reminder();
        reminder1.setId(1L);
        reminder1.setCustomerName("test1");

        Reminder reminder2 = new Reminder();
        reminder2.setId(2L);
        reminder2.setCustomerName("test2");

        List<Reminder> reminderList = Arrays.asList(reminder1, reminder2);

        when(reminderService.getAllReminders()).thenReturn(reminderList);

        mockMvc.perform(get("/api/reminders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].customerName").value("test1"))
                .andExpect(jsonPath("$[1].customerName").value("test2"));
    }

    @Test
    public void testScheduleReminder() throws Exception {
        ReminderRequest request = new ReminderRequest();
        request.setCustomerName("test");
        request.setProduct("laptop");
        request.setPurchaseDate(LocalDate.now());
        request.setWhatsappNumber("1234567890");
        request.setReminderTime(15);
        request.setMessage("test message");

        mockMvc.perform(post("/api/reminders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
