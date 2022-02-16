package org.launcode.Code.Food.models.data;

import org.launcode.Code.Food.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = ?1") //TODO: possibly change if email linking to SQL doesn't work
    User findByEmail(String email);
}
