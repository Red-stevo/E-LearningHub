package com.redstevo.code.Tables;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Entity
public class CourseCollectionTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String collectionName;

    @ManyToOne
    @JoinColumn(name = "collection_fk")
    private AuthTable authTable;
}
