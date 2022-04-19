package com.example.myweb.repository;

import java.util.List;

import com.example.myweb.model.House;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long>  {
}
