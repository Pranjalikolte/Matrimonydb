package com.matrimony.codewithnitin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matrimony.codewithnitin.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	List<Course> findByQualificationQualificationid(Long qualificationid);


}
