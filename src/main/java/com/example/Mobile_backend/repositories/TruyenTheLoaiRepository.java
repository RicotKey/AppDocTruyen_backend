package com.example.Mobile_backend.repositories;

import com.example.Mobile_backend.model.Truyen;
import com.example.Mobile_backend.model.TruyenTheLoai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TruyenTheLoaiRepository extends JpaRepository<TruyenTheLoai,Long> {
    List<TruyenTheLoai> findTruyenTheLoaiByIdtheloai(Long idtheloai);

    List<TruyenTheLoai> findTruyenTheLoaiById(Long id);
}