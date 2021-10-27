package com.example.demo.controllers;

import com.example.demo.api.facade.course.CourseFacade;
import com.example.demo.entity.UserRoleType;
import com.example.demo.model.course.CourseCreateRequestParams;
import com.example.demo.model.course.CourseDetailsResponse;
import com.example.demo.model.course.CoursesDetailsResponse;
import com.example.demo.service.AuthService.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/course")
@CrossOrigin("*")
public class CourseController {
    private final AuthService authService;
    private final CourseFacade courseFacade;


    public CourseController(AuthService authService, CourseFacade courseFacade) {
        this.authService = authService;
        this.courseFacade = courseFacade;
    }


    @GetMapping("/")
    public CoursesDetailsResponse getAll() {
        return courseFacade.getAll();
    }

    @GetMapping("/{ids}")
    public CoursesDetailsResponse findById(@PathVariable ArrayList<Long> ids) {
        return courseFacade.findByIds(ids);
    }

    @GetMapping("/subcategory/{ids}")
    public CoursesDetailsResponse getAllBySubCategories(@PathVariable ArrayList<Long> ids){
        System.out.println(ids);
        return courseFacade.getAllBySubCategories(ids);
    }

    @GetMapping("/category/{name}")
    public List<CourseDetailsResponse> getAllByCategory(@PathVariable String name){
        return courseFacade.getAllByCategory(name);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CourseCreateRequestParams course, @RequestHeader("Authorization") String token) {

        if (authService.isAuthorized(token, UserRoleType.TEACHER)) {
            return ResponseEntity.ok(courseFacade.create(course));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permission denied");
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CourseCreateRequestParams update, @RequestHeader("Authorization") String token) {


        if (authService.isAuthorized(token, UserRoleType.ADMIN)) {
            return ResponseEntity.ok(courseFacade.update(id, update));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permission denied");
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(@RequestParam Long id, @RequestHeader("Authorization") String token) {

        if (authService.isAuthorized(token, UserRoleType.ADMIN)) {
            courseFacade.delete(id);
            return ResponseEntity.ok("Course deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permission denied");
    }

    @GetMapping("/search/{value}")
    public List<CourseDetailsResponse> search(@PathVariable("value") String value) {
        return courseFacade.search(value);
    }

    @GetMapping("/teacher/{email}")
    public List<CourseDetailsResponse> getAllByOwner(@PathVariable("email") String email) {
        return courseFacade.getAllByOwner(email);
    }

    @GetMapping("/last10")
    public List<CourseDetailsResponse> getLast10() {
        return courseFacade.getLast10();
    }
}
