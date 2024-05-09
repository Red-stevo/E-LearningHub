package com.redstevo.code.Services;

import com.redstevo.code.CustomExceptions.CourseExistException;
import com.redstevo.code.Repositories.AuthRepository;
import com.redstevo.code.Repositories.CourseCollectionRepository;
import com.redstevo.code.Tables.CourseCollectionTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LearningService {

    private final CourseCollectionRepository courseCollectionRepository;

    private final AuthRepository authRepository;

    public ResponseEntity<HttpStatusCode> createCollection(
            Long userId, String collectionName, Boolean createDescriptionFile) {

        //ensure the collection name is unique for the user.
        if (collectionName.
                equals(courseCollectionRepository.findCollectionNameByAuthTable(authRepository.findByUserId(userId)
                                .orElseThrow(() -> new UsernameNotFoundException("user not found.")))
                        .orElse(null))) {
            throw new CourseExistException("Course Already Exist.");
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