package com.redstevo.code.Repositories;

import com.redstevo.code.Tables.AuthTable;
import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<AuthTable, Long> {

}
