package com.capstone.carInsurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.carInsurance.model.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

}
