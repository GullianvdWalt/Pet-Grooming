/*
 * Created By Gullian Van Der Walt - 18/09/2020
   Last Updated - 2020/09/18, 10:19
 */
package com.pg.pet_grooming.Repositories;


import com.pg.pet_grooming.Models.Past_Appointments_Pet_Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Past_Appointments_Pet_Services_Repo extends JpaRepository<Past_Appointments_Pet_Services, Integer>{
    
}
