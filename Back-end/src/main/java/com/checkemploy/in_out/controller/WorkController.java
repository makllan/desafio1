package com.checkemploy.in_out.controller;


import com.checkemploy.in_out.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/work")
@CrossOrigin(origins = "*")
public class WorkController {

    @Autowired
    private Services service;

        @PostMapping("/checkin/{employeeId}")
        public ResponseEntity<?> checkIn(@PathVariable Long employeeId) {
            try {
                return ResponseEntity.ok(service.checkIn(employeeId));
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }


        @PostMapping("/checkout/{employeeId}")
        public ResponseEntity<?> checkOut(@PathVariable Long employeeId) {
            try {
                return ResponseEntity.ok(service.checkOut(employeeId));
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }


        @GetMapping("/list")
        public ResponseEntity<?> listAll() {
            return ResponseEntity.ok(service.listAll());
        }
    }

