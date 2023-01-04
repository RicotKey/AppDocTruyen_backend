package com.example.Mobile_backend.controllers;

import com.example.Mobile_backend.model.ResponseObject;
import com.example.Mobile_backend.model.TheLoai;
import com.example.Mobile_backend.model.Truyen;
import com.example.Mobile_backend.model.TruyenTheLoai;
import com.example.Mobile_backend.repositories.TheLoaiRepository;
import com.example.Mobile_backend.repositories.TruyenRepository;
import com.example.Mobile_backend.repositories.TruyenTheLoaiRepository;
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
@RequestMapping(path = "/api/v1/TruyenTheLoai")
public class TruyenTheLoaiController {
    @Autowired
    private TheLoaiRepository repositorytl;
    @Autowired
    private TruyenTheLoaiRepository repository;
    @Autowired
    private TruyenRepository repositorytruyen;
    @GetMapping("{idtheloai}")
    ResponseEntity<ResponseObject> listTruyenTheLoai(@PathVariable Long idtheloai){
        List<TruyenTheLoai> foundidtruyens = repository.findTruyenTheLoaiByIdtheloai(idtheloai);
        List<Truyen> truyens = foundtruyen(foundidtruyens.get(0));
        for (int i = 1 ; i<foundidtruyens.size();i++) {
            truyens.addAll( foundtruyen(foundidtruyens.get(i)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Đổ dữ liệu thành công", truyens)
        );
    }
    @GetMapping("/Tim/{id}")
    ResponseEntity<ResponseObject> listTheLoaiTruyen(@PathVariable Long id){
        Optional<TruyenTheLoai> theLoai = repository.findById(id);
        Optional<TheLoai> tl = repositorytl.findById(theLoai.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Đổ dữ liệu thành công", tl)
        );
    }
    public List<Truyen> foundtruyen(TruyenTheLoai foundidtruyen)
    {
       List<Truyen> truyen = repositorytruyen.findTruyenById(foundidtruyen.getId());
       return truyen;
    }

}
