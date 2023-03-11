package com.example.qlsv.services;


import com.example.qlsv.model.SinhVien;

import java.sql.SQLException;
import java.util.List;



public interface SinhVienService {
    List<SinhVien> getAllStudents() throws SQLException;
	SinhVien saveStudent(SinhVien student) throws SQLException;
	SinhVien getStudentById(String id) throws SQLException;
	SinhVien updateStudent(SinhVien student);
	void deleteStudentById(String id);
    boolean checkLogin(String user, String pass) throws SQLException;
	String generateMssv() throws SQLException;
	boolean changPass(String user, String pass, String newPass) throws SQLException;
}
