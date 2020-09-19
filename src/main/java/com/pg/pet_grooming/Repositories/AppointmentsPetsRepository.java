/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pg.pet_grooming.Repositories;

import com.pg.pet_grooming.Models.Appointments_Pets;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentsPetsRepository extends JpaRepository<Appointments_Pets, Integer>{
    List<Appointments_Pets> findAll();
}
