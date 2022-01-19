package org.launcode.Code.Food.auth;

import java.util.Optional;

public interface ApplicationUserDAO {

    public Optional<ApplicationUser> selectApplicationUserByUsername(String username);

}
