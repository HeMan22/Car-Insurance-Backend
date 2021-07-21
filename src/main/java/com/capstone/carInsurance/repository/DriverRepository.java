package com.capstone.carInsurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.carInsurance.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {

}
