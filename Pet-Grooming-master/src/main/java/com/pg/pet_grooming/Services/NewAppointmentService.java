package com.pg.pet_grooming.Services;

import com.pg.pet_grooming.Repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class NewAppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;
}
