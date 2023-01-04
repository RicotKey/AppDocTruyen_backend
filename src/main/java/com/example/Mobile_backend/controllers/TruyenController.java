package com.example.Mobile_backend.controllers;

import com.example.Mobile_backend.model.ResponseObject;
import com.example.Mobile_backend.model.Truyen;
import com.example.Mobile_backend.repositories.TruyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Truyen")
public class TruyenController {

    @Autowired
    private TruyenRepository repository;

    @GetMapping("")
    ResponseEntity<ResponseObject> listTruyen(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Đổ dữ liệu thành công", repository.findAll())
        );
    }
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findbyId(@PathVariable Long id){
        Optional<Truyen> foundTruyen = repository.findById(id);
        return foundTruyen.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200,"Tìm thành công",foundTruyen)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(404,"Không tìm thấy truyện có id ="+id,"")
                );
    }
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertTruyen(@RequestBody Truyen newTruyen){
        List<Truyen> foundTruyen = repository.findByTentruyen(newTruyen.getTentruyen().trim());
        if(foundTruyen.size() >0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject(501,"Trùng tên truyện","")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Thêm truyện thành công", repository.save(newTruyen))
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateTruyen(@RequestBody Truyen newTruyen, @PathVariable Long id){
        Truyen updatedTruyen = repository.findById(id)
                .map(truyen -> {
                    truyen.setTentruyen(newTruyen.getTentruyen());
                    truyen.setMota(newTruyen.getMota());
                    truyen.setNgaycapnhat(newTruyen.getNgaycapnhat());
                    truyen.setTacgia(newTruyen.getTacgia());
                    truyen.setNgaydang(newTruyen.getNgaydang());
                    truyen.setTrangthai(newTruyen.isTrangthai());
                    truyen.setHinh(newTruyen.getHinh());
                    return repository.save(truyen);
                }).orElseGet(() -> {
                    newTruyen.setId(id);
                    return repository.save(newTruyen);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Update thành công", updatedTruyen)
        );
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteTruyen(@PathVariable Long id){
        boolean exitsts = repository.existsById(id);
        if(exitsts){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200,"Xóa thành công","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(404,"Không tìm thấy","")
        );
    }
}
