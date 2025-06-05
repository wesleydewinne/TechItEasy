package com.novi.techeasy.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/televisions")

public class TelevisionsController {

    private final List<String> database = List.of("Samsung", "LG", "Philips");;

    @GetMapping
    public ResponseEntity<List<String>> getTelevisions() {
        return ResponseEntity.ok(database);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getTelevisions(@PathVariable int id) {
        return ResponseEntity.ok(database.get(id));
    }

    @PostMapping
    public ResponseEntity<String> postTelevisions(@RequestBody String televisions) {
        return ResponseEntity.created(null).build();
    }

}
