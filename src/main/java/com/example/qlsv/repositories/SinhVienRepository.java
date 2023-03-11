package com.example.qlsv.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.qlsv.model.SinhVien;


@Repository
public class SinhVienRepository {
    private ConnectDataBase connect = new ConnectDataBase();

    public boolean checkLogin(String user, String pass) throws SQLException {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        PreparedStatement statement = connect.getConnection().prepareStatement(sql);
        statement.setString(1, user);
        statement.setString(2, pass);
        ResultSet rs = statement.executeQuery();
        rs.next();  
        int rowCount = rs.getRow(); 
        System.out.println(rowCount);
        return rowCount > 0;
    }

    public boolean changePassWorld(String user, String pass, String newPass) throws SQLException {
        if(checkLogin(user, pass)){
            String sql = "UPDATE Users SET password = ? WHERE username = ?";
            PreparedStatement statement = connect.getConnection().prepareStatement(sql);
            statement.setString(1, newPass);
            statement.setString(2, user);
            int rs = statement.executeUpdate();
            return rs > 0;
        } else{
            return false;
        }
        
        
    }

    public List<SinhVien> findAll() throws SQLException {
        String sql = "SELECT * FROM SinhVien";
        Statement statement = connect.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<SinhVien> list = new ArrayList<>();
        while (rs.next()) {
            SinhVien tmp = null;
            String maSV = rs.getString(1);
            String hoTen = rs.getString(2);
            Boolean gioiTinh = rs.getBoolean(3);
            int tuoi = rs.getInt(4);
            String sdt = rs.getString(5);
            String email = rs.getString(6);
            String diaChi = rs.getString(7);
            String lop = rs.getString(8);
            double gpa = rs.getDouble(9);
            tmp = new SinhVien(maSV, hoTen, tuoi, gioiTinh, diaChi, sdt, email, lop, Math.round(gpa*100.0)/100.0);
            list.add(tmp);
        }
        return list;
    }

    public SinhVien save(SinhVien student) throws SQLException {
        String sql = "INSERT INTO SinhVien VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ppsm = connect.getConnection().prepareStatement(sql);
        ppsm.setString(1, student.getId());
        ppsm.setString(2, student.getHoTen());
        ppsm.setBoolean(3, student.isGioiTinh());
        ppsm.setInt(4, student.getTuoi());
        ppsm.setString(5, student.getSdt());
        ppsm.setString(6, student.getEmail());
        ppsm.setString(7, student.getDiaChi());
        ppsm.setString(8, student.getLop());
        ppsm.setDouble(9, Math.round(student.getGpa()*100.0)/100.0);
        ppsm.execute();
        return student;
    }

    public String getMaxMssv() throws SQLException {
        String sql = "SELECT MAX(maSV) FROM SinhVien";
        Statement  query = connect.getConnection().createStatement();
        ResultSet rs = query.executeQuery(sql);
        rs.next();
        String maxMssv = rs.getString(1);
        return maxMssv;
    }

    public String generateMssv() throws SQLException {
        String maxMssv = getMaxMssv(); // lấy mã số sinh viên lớn nhất từ cơ sở dữ liệu
        int currentId = Integer.parseInt(maxMssv.substring(2)); // chuyển mã số sinh viên lớn nhất sang kiểu số và bỏ đi tiền tố "SV"
        currentId++;
        String idString = String.format("%04d", currentId); // chuyển giá trị id thành chuỗi độ dài 4 kí tự, bao gồm các số 0 còn thiếu
        String mssv = "SV" + idString; // thêm tiền tố SV vào chuỗi id
        return mssv;
    }

    public SinhVien findById(String id) throws SQLException {
        String sql = "SELECT * FROM SinhVien WHERE maSV = ?";
        PreparedStatement ppsm = connect.getConnection().prepareStatement(sql);
        ppsm.setString(1, id);
        ResultSet rs = ppsm.executeQuery();
        rs.next();
        SinhVien tmp = null;
        String maSV = rs.getString(1);
        String hoTen = rs.getString(2);
        Boolean gioiTinh = rs.getBoolean(3);
        int tuoi = rs.getInt(4);
        String sdt = rs.getString(5);
        String email = rs.getString(6);
        String diaChi = rs.getString(7);
        String lop = rs.getString(8);
        double gpa = rs.getDouble(9);
        tmp = new SinhVien(maSV, hoTen, tuoi, gioiTinh, diaChi, sdt, email, lop,Math.round(gpa*100.0)/100.0);
        return tmp;
    }

    public SinhVien update(SinhVien student) {
        String sql = "UPDATE SinhVien SET ten = ?, gioiTinh = ?, tuoi = ?, sdt = ?, email = ?, diaChi = ?, lop = ?, gpa = ? WHERE maSV = ?";
        try {
            PreparedStatement ppsm = connect.getConnection().prepareStatement(sql);
            
            ppsm.setString(1, student.getHoTen());
            ppsm.setBoolean(2, student.isGioiTinh());
            ppsm.setInt(3, student.getTuoi());
            ppsm.setString(4, student.getSdt());
            ppsm.setString(5, student.getEmail());
            ppsm.setString(6, student.getDiaChi());
            ppsm.setString(7, student.getLop());
            ppsm.setDouble(8, Math.round(student.getGpa()*100.0)/100.0);
            ppsm.setString(9, student.getId());
            ppsm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public void delete(String id) {
        String sql = "DELETE FROM SinhVien WHERE maSV = ?";
        try {
            PreparedStatement ppsm = connect.getConnection().prepareStatement(sql);
            ppsm.setString(1, id);
            ppsm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
