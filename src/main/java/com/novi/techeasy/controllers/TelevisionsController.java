package com.novi.techeasy.controllers;

import com.novi.techeasy.models.Television;
import com.novi.techeasy.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/televisions")

public class TelevisionsController {

    private final TelevisionRepository televisionRepository;

    public TelevisionsController(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }


    @GetMapping
    public ResponseEntity<List<Television>> getTelevisions() {
        List<Television> televisions = televisionRepository.findAll();
        return ResponseEntity.ok(televisions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevisionById(@PathVariable Long id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        return optionalTelevision
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Television> postTelevisions(@RequestBody Television television) {
        Television savedTelevision = televisionRepository.save(television);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTelevision.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putTelevisions(@PathVariable Long id, @RequestBody Television updatedTelevision) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);

        if (optionalTelevision.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Television existingTelevision = optionalTelevision.get();

        existingTelevision.setBrand(updatedTelevision.getBrand());
        existingTelevision.setType(updatedTelevision.getType());
        existingTelevision.setName(updatedTelevision.getName());
        existingTelevision.setPrice(updatedTelevision.getPrice());
        existingTelevision.setAvailableSize(updatedTelevision.getAvailableSize());
        existingTelevision.setRefreshRate(updatedTelevision.getRefreshRate());
        existingTelevision.setScreenType(updatedTelevision.getScreenType());
        existingTelevision.setScreenQuality(updatedTelevision.getScreenQuality());
        existingTelevision.setSmartTv(updatedTelevision.getSmartTv());
        existingTelevision.setWifi(updatedTelevision.getWifi());
        existingTelevision.setVoiceControl(updatedTelevision.getVoiceControl());
        existingTelevision.setHdr(updatedTelevision.getHdr());
        existingTelevision.setBluetooth(updatedTelevision.getBluetooth());
        existingTelevision.setAmbiLight(updatedTelevision.getAmbiLight());
        existingTelevision.setOriginalStock(updatedTelevision.getOriginalStock());
        existingTelevision.setSold(updatedTelevision.getSold());

        televisionRepository.save(existingTelevision);

        return ResponseEntity.ok("Televisie is bijgewerkt");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelevision(@PathVariable Long id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);

        if (optionalTelevision.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        televisionRepository.deleteById(id);
        return ResponseEntity.ok("Televisie is verwijderd");
    }
}
