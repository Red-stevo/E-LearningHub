package com.redstevo.code.Repositories;

import com.redstevo.code.Tables.AuthTable;
import com.redstevo.code.Tables.RefreshTokenTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RefreshTokenRepository extends CrudRepository<RefreshTokenTable, Long> {


    void deleteByAuthTable(AuthTable authTable);
}
