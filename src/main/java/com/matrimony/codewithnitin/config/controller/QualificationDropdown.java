package com.matrimony.codewithnitin.config.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.codewithnitin.entity.Course;
import com.matrimony.codewithnitin.entity.Qualification;
import com.matrimony.codewithnitin.entity.Stream;
import com.matrimony.codewithnitin.repository.CourseRepository;
import com.matrimony.codewithnitin.repository.QualificationRepository;
import com.matrimony.codewithnitin.repository.StreamRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class QualificationDropdown {

	@Autowired
	StreamRepository streamRepository;

	@Autowired
	QualificationRepository qualificationRepository;

	@Autowired
	CourseRepository courseRepository;

	@GetMapping("/stream")
	public List<Stream> getAllStreams() {
		return streamRepository.findAll();
	}

	@GetMapping("/qualification/{streamid}")
	public ResponseEntity<Map<String, List<Map<String, Object>>>> getQualificationByStreamId(
			@PathVariable Long streamid) {
		List<Qualification> qualifications = qualificationRepository.findByStreamStreamid(streamid);
		List<Map<String, Object>> DataList = new ArrayList<>();
		for (Qualification qualification : qualifications) {
			Map<String, Object> Data = new HashMap<>();
			Data.put("qualificationid", qualification.getQualificationid());
			Data.put("qualificationName", qualification.getQualificationName());
			DataList.add(Data);
		}
		Map<String, List<Map<String, Object>>> response = new HashMap<>();
		response.put("qualifications", DataList);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/courses/{qualificationid}")
	public ResponseEntity<Map<String, List<Map<String, Object>>>> getCourseByQualificationId(
			@PathVariable Long qualificationid) {
		List<Course> courses = courseRepository.findByQualificationQualificationid(qualificationid);
		List<Map<String, Object>> DataList = new ArrayList<>();
		for (Course course : courses) {
			Map<String, Object> Data = new HashMap<>();
			Data.put("courseid", course.getCourseid());
			Data.put("courseName", course.getCourseName());
			DataList.add(Data);
		}
		Map<String, List<Map<String, Object>>> response = new HashMap<>();
		response.put("courses", DataList);
		return ResponseEntity.ok(response);
	}

}
