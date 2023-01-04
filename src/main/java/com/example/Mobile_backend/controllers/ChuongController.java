package com.example.Mobile_backend.controllers;

import com.example.Mobile_backend.model.Chuong;
import com.example.Mobile_backend.model.ResponseObject;
import com.example.Mobile_backend.model.TheLoai;
import com.example.Mobile_backend.repositories.ChuongRepository;
import com.example.Mobile_backend.repositories.TheLoaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Chuong")
public class ChuongController {
    @Autowired
    private ChuongRepository repository;
    @GetMapping("/{idtruyen}")
    ResponseEntity<ResponseObject> findbyIdTruyen(@PathVariable Long idtruyen) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Tìm thành công", repository.findByIdtruyen(idtruyen))
                );
    }
    @GetMapping("/dem/{idtruyen}")
    ResponseEntity<ResponseObject> countbyIdTruyen(@PathVariable Long idtruyen) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Tìm thành công", repository.countByIdtruyen(idtruyen))
        );
    }
}
