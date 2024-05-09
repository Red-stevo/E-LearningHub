package com.redstevo.code.Controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/learn")
public class LearningController {

    @PostMapping("/new/collection")
    public ResponseEntity<Model> createCollection(
            @RequestParam String CollectionName, @RequestParam Boolean CreateDescFile){



        return null;
    }

}
