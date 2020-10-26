/*
 * Created By Gullian Van Der Walt - 19/09/2020
 * Last Updated - 2020/09/19, 11:41
 * 
 * DTO Class for custom query defined in PetRepository to join pet and pet_owner.
 */
package com.pg.pet_grooming.DTO;

public class Pet_PetOwner {

    private Integer pet_owner_id;
    private String pet_owner_full_name;
    private String pet_owner_cell;
    private String pet_owner_address;
    private String pet_owner_city;
    private Integer pet_id;
    private String pet_name;
    private String pet_breed;
    

    public Pet_PetOwner() {
    }

    public Pet_PetOwner(Integer id, String petOwnerFullName,
            String petOwnerCell, String petOwnerAddress,String petOwnerCity,
            Integer pet_id, String pet_name, String pet_breed) {

        this.pet_owner_id = id;
        this.pet_owner_full_name = petOwnerFullName;
        this.pet_owner_cell = petOwnerCell;
        this.pet_owner_address = petOwnerAddress;
        this.pet_owner_city = petOwnerCity;
        this.pet_id = pet_id;
        this.pet_name = pet_name;
        this.pet_breed = pet_breed;
    }

    public Integer getPet_owner_id() {
        return pet_owner_id;
    }

    public void setPet_owner_id(Integer pet_owner_id) {
        this.pet_owner_id = pet_owner_id;
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

    public Integer getPet_id() {
        return pet_id;
    }

    public void setPet_id(Integer pet_id) {
        this.pet_id = pet_id;
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

    public String getPet_owner_city() {
        return pet_owner_city;
    }

    public void setPet_owner_city(String pet_owner_city) {
        this.pet_owner_city = pet_owner_city;
    }

    @Override
    public String toString() {
        return "Pet_PetOwner{" + "pet_owner_id=" + pet_owner_id + ""
                + ", pet_owner_full_name=" + pet_owner_full_name + ""
                + ", pet_owner_cell=" + pet_owner_cell + ""
                + ", pet_owner_address=" + pet_owner_address + ""
                + ", pet_id=" + pet_id + ", pet_name=" + pet_name + ""
                + ", pet_breed=" + pet_breed + '}';
    }

}
