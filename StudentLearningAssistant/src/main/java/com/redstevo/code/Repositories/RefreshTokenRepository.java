package com.redstevo.code.Repositories;

import com.redstevo.code.Tables.RefreshTokenTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshTokenTable, Long> {

}
