package com.example.Mobile_backend.repositories;

import com.example.Mobile_backend.model.Chuong;
import com.example.Mobile_backend.model.Truyen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChuongRepository extends JpaRepository<Chuong, Long> {
    List<Chuong> findByIdtruyen(Long idtruyen);

    Integer countByIdtruyen(Long idtruyen);
}