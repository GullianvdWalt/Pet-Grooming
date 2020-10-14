/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pg.pet_grooming.Repositories;

// Imnports
import com.pg.pet_grooming.Models.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Gullian
 */
public interface AppointmentRepository extends JpaRepository<Appointments, Long> {

}
