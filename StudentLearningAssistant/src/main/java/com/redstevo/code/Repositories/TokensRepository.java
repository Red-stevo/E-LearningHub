package com.redstevo.code.Repositories;

import com.redstevo.code.Tables.AuthTable;
import com.redstevo.code.Tables.TokensTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokensRepository extends CrudRepository<TokensTable, Long> {

    Optional<TokensTable> findByToken(String token);

    Optional<List<TokensTable>> findByAuthTable(AuthTable authTable);
}
