package com.redstevo.code.Services;

import com.redstevo.code.CustomExceptions.CourseExistException;
import com.redstevo.code.Repositories.AuthRepository;
import com.redstevo.code.Repositories.CourseCollectionRepository;
import com.redstevo.code.Tables.CourseCollectionTable;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LearningService {

    private final CourseCollectionRepository courseCollectionRepository;

    private final AuthRepository authRepository;

    public ResponseEntity<HttpStatusCode> createCollection(
            @Validated @NotBlank(message = "user id can not be blank") Long userId,
            @Validated @NotBlank(message = "collection name can not be blank") String collectionName,
            Boolean createDescriptionFile) {

        /*get user course collections.*/
        List<String> collections = courseCollectionRepository
                .findCollectionNameByAuthTable(authRepository.findByUserId(userId)
                        .orElseThrow(() -> new UsernameNotFoundException("user not found.")))
                .orElse(null);

        //ensure the collection name is unique for the user.

        if(collections != null) {
            collections.forEach((collection) -> {
                if (collectionName.equals(collection)) {
                    throw new CourseExistException("Course Already Exist.");
                }
            });
        }


        //add the new collection.
        CourseCollectionTable courseCollectionTable = new CourseCollectionTable();
        courseCollectionTable.setCollectionName(collectionName);
        courseCollectionTable.setAuthTable(authRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("user not found.")));
        
        courseCollectionRepository.save(courseCollectionTable);


        /*I will log in for addition on the description file.*/
        
        log.info("course collection created");
        return new ResponseEntity<>(HttpStatusCode.valueOf(201));
    }
}