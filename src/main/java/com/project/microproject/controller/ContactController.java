package com.project.microproject.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class ContactController {

    @PostMapping("/contact")
    public ResponseEntity<?> receiveContactForm(@RequestBody ContactForm form) {
        System.out.println("Received contact form:");
        System.out.println("Name: " + form.getName());
        System.out.println("Email: " + form.getEmail());
        System.out.println("Message: " + form.getMessage());

        // TODO: Save data to DB, send confirmation email or notifications here

        return ResponseEntity.ok().body(Collections.singletonMap("message", "Message received"));
    }

    // Static inner class to model contact form data
    public static class ContactForm {
        private String name;
        private String email;
        private String message;

        // Getters and Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}
