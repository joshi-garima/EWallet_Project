package com.majorProject.UserService.Repository;

import com.majorProject.UserService.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByEmail(String  email);
}
