package com.example.qlsv.services.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.qlsv.model.SinhVien;
import com.example.qlsv.repositories.SinhVienRepository;
import com.example.qlsv.services.SinhVienService;

@Service
public class SinhVienImpl implements SinhVienService {
    private SinhVienRepository sinhVienRepository;
    
    public SinhVienImpl(SinhVienRepository sinhVienRepository){
        super();
        this.sinhVienRepository = sinhVienRepository;
    }

    @Override
    public List<SinhVien> getAllStudents() throws SQLException {
        return sinhVienRepository.findAll();
    }

    @Override
    public SinhVien saveStudent(SinhVien student) throws SQLException {
        return sinhVienRepository.save(student);
    }

    @Override
    public SinhVien getStudentById(String id) throws SQLException {
        return sinhVienRepository.findById(id);
    }

    @Override
    public SinhVien updateStudent(SinhVien student) {
        return sinhVienRepository.update(student);
    }

    @Override
    public void deleteStudentById(String id) {
        sinhVienRepository.delete(id);
    }

    @Override
    public boolean checkLogin(String user, String pass) throws SQLException {
        return sinhVienRepository.checkLogin(user, pass);
    }

    @Override
    public String generateMssv() throws SQLException {
        return sinhVienRepository.generateMssv();
    }

    @Override
    public boolean changPass(String user, String pass, String newPass) throws SQLException {
        return sinhVienRepository.changePassWorld(user, pass, newPass);
    }
    
}
