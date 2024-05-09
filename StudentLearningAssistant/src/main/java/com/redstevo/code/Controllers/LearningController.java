package com.redstevo.code.Controllers;


import com.redstevo.code.Services.LearningService;
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

    private final LearningService learningService;

    /*
    * This end point will :-
    *   1. Create a new Collection.
    *   2. If User allows a new Description file will be created to describe the course.
    *   3. Relate the Collection to a user and the Description file to the collection.
    */

    @PostMapping("/new/collection/{user-id}")
    public ResponseEntity<Model> createCollection(
            @PathVariable("user-id") String userId,
            @RequestParam("collectionName") String collectionName,
            @RequestParam("createDescriptionFile") Boolean createDescriptionFile){
        log.info("Request to create a new collection.");


        return null;
    }

}
