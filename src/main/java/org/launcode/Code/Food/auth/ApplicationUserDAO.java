package org.launcode.Code.Food.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ApplicationUserDAO {

    public Optional<ApplicationUser> selectApplicationUserByUsername(String username);

}
