package com.redstevo.code.Repositories;

import com.redstevo.code.Tables.AuthTable;
import com.redstevo.code.Tables.RefreshTokenTable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Repository
@Transactional
public interface RefreshTokenRepository extends CrudRepository<RefreshTokenTable, Long> {


    void deleteByAuthTable(AuthTable authTable);

    @Query("SELECT t.expirationDate FROM RefreshTokenTable AS t WHERE t.refreshToken =:refreshToken")
    Optional<Date> findExpirationDateByRefreshToken(String refreshToken);

    @Query("SELECT t.authTable FROM RefreshTokenTable AS t WHERE t.refreshToken =:refreshToken")
    AuthTable findAuthTableByRefreshToken(String refreshToken);
}
