package com.example.Mobile_backend.repositories;

import com.example.Mobile_backend.model.TheLoai;
import com.example.Mobile_backend.model.Truyen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheLoaiRepository extends JpaRepository<TheLoai, Long> {
    List<TheLoai> findByTentheloai(String tentheloai);
    List<TheLoai> findTheLoaiById(Long id);


}
