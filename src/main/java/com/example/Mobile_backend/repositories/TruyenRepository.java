package com.example.Mobile_backend.repositories;

import com.example.Mobile_backend.model.TheLoai;
import com.example.Mobile_backend.model.Truyen;
import com.example.Mobile_backend.model.TruyenTheLoai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TruyenRepository extends JpaRepository<Truyen, Long> {
    List<Truyen> findByTentruyen(String tentruyen);
    List<Truyen> findTruyenById(Long id);


}