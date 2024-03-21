package com.redstevo.code.Repositories;

import com.redstevo.code.Tables.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<UserProfile, String> {

}
