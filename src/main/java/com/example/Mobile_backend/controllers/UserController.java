package com.example.Mobile_backend.controllers;

import com.example.Mobile_backend.model.ResponseObject;
import com.example.Mobile_backend.model.Truyen;
import com.example.Mobile_backend.model.User;
import com.example.Mobile_backend.repositories.TruyenRepository;
import com.example.Mobile_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/User")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping("")
    ResponseEntity<ResponseObject> listUser(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Đổ dữ liệu thành công", repository.findAll())
        );
    }
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser){
        List<User> founduser = repository.findByTenuser(newUser.getTenuser().trim());
        if(founduser.size() >0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject(501,"Trùng tên truyện","")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Thêm truyện thành công", repository.save(newUser))
        );
    }
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser, @PathVariable Long id){
        User updatedUser = repository.findById(id)
                .map(user -> {
                    user.setTenuser(newUser.getTenuser());
                    user.setMatkhau(newUser.getMatkhau());
                    user.setEmail(newUser.getEmail());
                    user.setHinh(newUser.getHinh());
                    user.setTrangthai(newUser.isTrangthai());
                    return repository.save(user);
                }).orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Update thành công", updatedUser)
        );
    }
}
