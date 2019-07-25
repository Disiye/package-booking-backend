package com.oocl.packagebooking.service;

import com.oocl.packagebooking.entity.PackageBoy;
import com.oocl.packagebooking.repository.PackageBoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PackageBoyService {
    @Autowired
    public PackageBoyRepository packageBoyRepository;

    public List<PackageBoy> getAllPackage() {
        return packageBoyRepository.findAll();
    }

    public void addPackage(PackageBoy packageBoy) {
        packageBoy.setStatus("未预约");
        packageBoy.setAppointmentTime(null);
        packageBoyRepository.save(packageBoy);
    }

    public ResponseEntity updatePackage(PackageBoy packageBoy, String id) {
        PackageBoy packageBoyDB = packageBoyRepository.findById(id).get();
        if ("".equals(packageBoy.getStatus()) && "".equals(packageBoy.getName())){
            packageBoyDB.setAppointmentTime(packageBoy.getAppointmentTime());
            packageBoyDB.setStatus("已预约");
        }else{
            packageBoyDB.setStatus("已取件");
        }

        packageBoyRepository.save(packageBoyDB);
        return ResponseEntity.ok().body(packageBoyDB);
    }
}
