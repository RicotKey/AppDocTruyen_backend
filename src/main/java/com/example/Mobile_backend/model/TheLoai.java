package com.example.Mobile_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name="theloai")
public class TheLoai {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tentheloai;

    public TheLoai(){}
    public TheLoai(String tentheloai) {
        this.tentheloai = tentheloai;
    }

    public Long getMatheloai() {
        return id;
    }

    public void setMatheloai(Long matheloai) {
        id = matheloai;
    }

    public String getTentheloai() {
        return tentheloai;
    }

    public void setTentheloai(String tentheloai) {
        this.tentheloai = tentheloai;
    }
}
