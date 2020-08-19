package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
