package com.redstevo.code.Services;

import com.redstevo.code.Repositories.CourseCollectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Slf4j
@Service
@RequiredArgsConstructor
public class LearningService {

    private final CourseCollectionRepository courseCollectionRepository;

    public ResponseEntity<HttpStatusCode>
    createCollection(String userId, String collectionName, Boolean createDescriptionFile) {

        return null;
    }
}
