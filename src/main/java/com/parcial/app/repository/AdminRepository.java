package com.parcial.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parcial.app.entity.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long>{

}