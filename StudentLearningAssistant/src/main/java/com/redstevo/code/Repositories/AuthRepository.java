package com.redstevo.code.Repositories;

import com.redstevo.code.Tables.AuthTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface AuthRepository extends CrudRepository<AuthTable, Long> {

    Optional<AuthTable> findByUsername(String username);

    Optional<Integer> countAllByUsername(String username);

    Optional<AuthTable> findByUserId(Long userId);
}
