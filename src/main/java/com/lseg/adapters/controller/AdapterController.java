package com.lseg.adapters.controller;

import com.lseg.adapters.dto.DataDto;
import com.lseg.adapters.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/adapter")
public class AdapterController<T> {

    @Autowired
    private DataService dataService;

    @PostMapping("/process")
    public ResponseEntity<String> processAndSave(@RequestBody DataDto<T> dto) {
        dataService.processAndSaveToRedis(dto);
        dataService.processAndSaveToPostgres(dto);
        return ResponseEntity.ok("Data processed and saved successfully");
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<T> retrieveDataFromRedis(@PathVariable Long id) {
        T data = (T)dataService.retrieveDataFromRedis(id);
        return ResponseEntity.ok(data);
    }
}
