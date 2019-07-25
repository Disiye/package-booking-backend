package com.oocl.packagebooking.service;

import com.oocl.packagebooking.entity.PackageBoy;
import com.oocl.packagebooking.repository.PackageBoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageBoyService {
    @Autowired
    public PackageBoyRepository packageBoyRepository;

    public List<PackageBoy> getAllPackage() {
        return packageBoyRepository.findAll();
    }

    public void addPackage(PackageBoy packageBoy) {
        packageBoyRepository.save(packageBoy);
    }

    public ResponseEntity updatePackage(PackageBoy packageBoy, String id) {
        PackageBoy packageBoyDB = packageBoyRepository.findById(id).get();
        packageBoyDB.setStatus(packageBoy.getStatus());
        packageBoyDB.setAppointmentTime(packageBoy.getAppointmentTime());
        packageBoyRepository.save(packageBoyDB);
        return ResponseEntity.ok().body(packageBoyDB);
    }
}
