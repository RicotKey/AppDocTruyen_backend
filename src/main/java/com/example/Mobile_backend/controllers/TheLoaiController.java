package com.example.Mobile_backend.controllers;

import com.example.Mobile_backend.model.ResponseObject;
import com.example.Mobile_backend.model.TheLoai;
import com.example.Mobile_backend.model.Truyen;
import com.example.Mobile_backend.repositories.TheLoaiRepository;
import com.example.Mobile_backend.repositories.TruyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/TheLoai")
public class TheLoaiController {
    @Autowired
    private TheLoaiRepository repository;

    @GetMapping("")
    ResponseEntity<ResponseObject> listTheLoai(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Đổ dữ liệu thành công", repository.findAll())
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findbyId(@PathVariable Long id){
        Optional<TheLoai> foundTheLoai = repository.findById(id);
        return foundTheLoai.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200,"Tìm thành công",foundTheLoai)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(404,"Không tìm thấy thể loại có id ="+id,"")
                );
    }
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertTheLoai(@RequestBody TheLoai newTheLoai){
        List<TheLoai> foundTheLoai = repository.findByTentheloai(newTheLoai.getTentheloai().trim());
        if(foundTheLoai.size() >0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject(501,"Trùng tên thể loại","")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Thêm thể loại thành công", repository.save(newTheLoai))
        );
    }
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateTheLoai(@RequestBody TheLoai newTheLoai, @PathVariable Long id){
        TheLoai updatedTheLoai = repository.findById(id)
                .map(theLoai -> {
                    theLoai.setMatheloai(newTheLoai.getMatheloai());
                    theLoai.setTentheloai(newTheLoai.getTentheloai());
                    return repository.save(theLoai);
                }).orElseGet(() -> {
                    newTheLoai.setMatheloai(id);
                    return repository.save(newTheLoai);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Update thành công", updatedTheLoai)
        );
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteTheLoai(@PathVariable Long id){
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
