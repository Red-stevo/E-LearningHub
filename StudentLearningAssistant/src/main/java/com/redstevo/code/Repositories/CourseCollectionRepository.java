package com.redstevo.code.Repositories;

import com.redstevo.code.Tables.AuthTable;
import com.redstevo.code.Tables.CourseCollectionTable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CourseCollectionRepository extends CrudRepository<CourseCollectionTable, Long> {


    @Query("SELECT T.collectionName FROM CourseCollectionTable as T WHERE T.authTable =:authTable")
    String findCollectionNameByAuthTable(AuthTable authTable);
}
