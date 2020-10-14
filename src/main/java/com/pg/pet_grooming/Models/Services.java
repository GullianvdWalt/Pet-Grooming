/*
 * Created By Gullian Van Der Walt - 01/08/2020
   Last Updated - 2020/09/18, 10:19
 * This is the Services (Entity/Table)
 * 
 */
package com.pg.pet_grooming.Models;

// Imports
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "services")
@Getter
@Setter
@ToString
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo 
public class Services extends Auditable<String> {

    // Services Attributes
    // Primary Key
    @Id
    @Column(name = "service_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer service_id;

    @Column(name = "service_name", length = 255, nullable = false)
    private String service_name;

    @Column(name = "service_icon", length = 255, nullable = true)
    private String service_icon;

    @Column(name = "service_price", length = 255, nullable = false)
    private double service_price;

    @Column(name = "service_status", nullable = false)
    private boolean service_status;

    @Transient
    public String getImagePath() {
        if (service_icon == null || service_id == null) {
            return null;
        }

        return "/Icon/" + service_id + "/" + service_icon;
    }

    // Services
    // Appointment - Services - Appointment_Pet_Services - Pet
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "appointments_pet_services",
            joinColumns = @JoinColumn(name = "service_id", referencedColumnName = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "app_id", referencedColumnName = "app_id"))
    private List<Appointments> appointment;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "past_appointments_pet_services",
            joinColumns = @JoinColumn(name = "service_id", referencedColumnName = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "p_app_id", referencedColumnName = "id"))
    private List<PastAppointments> pastAppointments;

}

