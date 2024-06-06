package com.parcial.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parcial.app.entity.Idea;


public interface IdeaRepository extends JpaRepository<Idea, Long>{

}
