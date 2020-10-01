/*
 * Created By Gullian Van Der Walt 10/09/2020
 * 
 * Last Update : 2020/09/11, 06:40

   Entity for Joining Pet Owner and Pet

 */
package com.pg.pet_grooming.DTO;


public class PetOwnerPet implements java.io.Serializable{
    
    private Integer id;
    private String pet_owner_full_name;
    private String pet_owner_cell;
    private String pet_owner_address;
    private String pet_name;
    private String pet_breed;
            
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPet_owner_full_name() {
        return pet_owner_full_name;
    }

    public void setPet_owner_full_name(String pet_owner_full_name) {
        this.pet_owner_full_name = pet_owner_full_name;
    }

    public String getPet_owner_cell() {
        return pet_owner_cell;
    }

    public void setPet_owner_cell(String pet_owner_cell) {
        this.pet_owner_cell = pet_owner_cell;
    }

    public String getPet_owner_address() {
        return pet_owner_address;
    }

    public void setPet_owner_address(String pet_owner_address) {
        this.pet_owner_address = pet_owner_address;
    }

    public String getPet_name() {
        return pet_name;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public String getPet_breed() {
        return pet_breed;
    }

    public void setPet_breed(String pet_breed) {
        this.pet_breed = pet_breed;
    }

     
    
    public PetOwnerPet(Integer id, String pet_owner_full_name, 
            String pet_owner_cell, String pet_owner_address, 
            String pet_name,String pet_breed) {
        this.id = id;
        this.pet_owner_full_name = pet_owner_full_name;
        this.pet_owner_cell = pet_owner_cell;
        this.pet_owner_address = pet_owner_address;
        this.pet_name = pet_name;
        this.pet_breed = pet_breed;
//        this.pet_names = pet_names;
//        this.pet_breeds = pet_breeds;

    }

    public PetOwnerPet() {
    }





    
    
}
