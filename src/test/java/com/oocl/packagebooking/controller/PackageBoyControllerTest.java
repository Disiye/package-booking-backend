package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.entity.PackageBoy;
import com.oocl.packagebooking.repository.PackageBoyRepository;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PackageBoyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PackageBoyRepository packageBoyRepository;

    @Before
    public void deleteAll() {
        packageBoyRepository.deleteAll();
    }

    @Test
    public void should_return_status_is_created_when_post_new_package_boy() throws Exception {
        // GIVEN
        SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr = dataformat.format(new Date());
        PackageBoy packageBoy = new PackageBoy("101","test101","13145201314","已预约", datestr,1.00);

        // WHEN + THEN
        String objectJson = new JSONObject(packageBoy).toString();
        this.mockMvc.perform(post("/package-boys").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectJson)).andExpect(status().isCreated());
        Assertions.assertNotNull(packageBoyRepository.findById("101"));
    }

    @Test
    public void should_return_all_package_when_get_all_package() throws Exception {
        // GIVEN
        SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr = dataformat.format(new Date());
        PackageBoy packageBoy1 = new PackageBoy("101","test101","13145201314","已预约", datestr,1.00);
        PackageBoy packageBoy2 = new PackageBoy("102","test102","13145201314","已预约", datestr,1.00);

        // WHEN + THEN
        String objectJson1 = new JSONObject(packageBoy1).toString();
        this.mockMvc.perform(post("/package-boys").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectJson1)).andExpect(status().isCreated());

        String objectJson2 = new JSONObject(packageBoy2).toString();
        this.mockMvc.perform(post("/package-boys").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectJson2)).andExpect(status().isCreated());

        Assertions.assertEquals(2, packageBoyRepository.findAll().size());
    }

    @Test
    public void should_return_new_package_when_update_package() throws Exception {
        //Given
        SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr1 = dataformat.format(new Date());
        String datestr2 = dataformat.format(new Date());
        PackageBoy packageBoy = new PackageBoy("101","test101","13145201314","已取件", datestr1,1.00);
        String objectJson = new JSONObject(packageBoy).toString();
        this.mockMvc.perform(post("/package-boys").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectJson)).andExpect(status().isCreated());


        //When
        PackageBoy newPackageBoy = new PackageBoy("101","test102","13145201315","已取件", datestr2,2.00);
        String newObjectJson = new JSONObject(newPackageBoy).toString();
        String content = this.mockMvc.perform(put("/package-boys/101").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(newObjectJson)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        //Than
        assertEquals("已取件", obj.get("status"));
        assertEquals(datestr2, obj.get("appointmentTime"));
    }
}