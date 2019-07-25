package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.entity.PackageBoy;
import com.oocl.packagebooking.service.PackageBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostRemove;
import java.util.List;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/package-boys")
public class PackageBoyController {

    @Autowired
    public PackageBoyService packageBoyService;

    @GetMapping
    public List<PackageBoy> getAllPackage() {
        return packageBoyService.getAllPackage();
    }

    @PostMapping
    public ResponseEntity addPackage(@RequestBody PackageBoy packageBoy) {
        packageBoyService.addPackage(packageBoy);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity updatePackage(@RequestBody PackageBoy packageBoy, @PathVariable String id) {
        return packageBoyService.updatePackage(packageBoy, id);
    }
}
