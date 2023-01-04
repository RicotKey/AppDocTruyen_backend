package com.example.Mobile_backend.repositories;

import com.example.Mobile_backend.model.Truyen;
import com.example.Mobile_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByTenuser(String tenuser);
}
