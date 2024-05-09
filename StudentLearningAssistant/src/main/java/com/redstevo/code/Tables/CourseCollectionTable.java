package com.redstevo.code.Tables;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Entity
public class CourseCollectionTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String CollectionName;

    @ManyToOne()
    @JoinColumn(name = "collection_fk")
    private AuthTable authTable;
}
