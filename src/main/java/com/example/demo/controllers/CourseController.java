package com.example.demo.controllers;

import com.example.demo.api.facade.course.CourseFacade;
import com.example.demo.model.course.CourseCreateRequestParams;
import com.example.demo.model.course.CourseDetailsResponse;
import com.example.demo.model.course.CoursesDetailsResponse;
import com.example.demo.service.AuthService.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/course")
public class CourseController {
    private final AuthService authService;
    private final CourseFacade courseFacade;

    public CourseController(AuthService authService, CourseFacade courseFacade) {
        this.authService = authService;
        this.courseFacade = courseFacade;
    }

    //~CGLIB
    //JDK proxy
    @GetMapping("/")
    public CoursesDetailsResponse getAll() {
        return courseFacade.getAll();
    }

    @GetMapping("/{id}")
    public CourseDetailsResponse findById(@PathVariable("id") Long id) {
        return courseFacade.findById(id);
    }

    @GetMapping("/subcategory/{id}")
    public List<CourseDetailsResponse> getAllBySubcategory(@PathVariable Long id){
        return courseFacade.getAllBySubCategory(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CourseCreateRequestParams course, @RequestHeader("Authorization") String token) {


        if (authService.isAuthorized(token, "TEACHER")) {
            return ResponseEntity.ok(courseFacade.create(course));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permission denied");
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CourseCreateRequestParams update, @RequestHeader("Authorization") String token) {


        if (authService.isAuthorized(token, "admin")) {
            return ResponseEntity.ok(courseFacade.update(id, update));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permission denied");
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(@RequestParam Long id, @RequestHeader("Authorization") String token) {

        if (authService.isAuthorized(token, "admin")) {
            courseFacade.delete(id);
            return ResponseEntity.ok("Course deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permission denied");
    }
}
