package com.example.qlsv.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.qlsv.model.SinhVien;
import com.example.qlsv.services.SinhVienService;

@Controller
public class SinhVienController {
    private SinhVienService sinhVienService;
    private static String idLog;
    
    public SinhVienController(SinhVienService sinhVienService){
        super();
        this.sinhVienService = sinhVienService;
    }

    @GetMapping("/students")
	public String listStudents(Model model) throws SQLException {
		model.addAttribute("students", sinhVienService.getAllStudents());
		return "students";
	}

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/home")
    public String login(@RequestParam("user") String user, @RequestParam("pass") String pass, Model model) throws SQLException {
        if (user.equals("admin") && pass.equals("1234")) {
            model.addAttribute("students", sinhVienService.getAllStudents());
            return "home";
        } else {
            try {
                if( sinhVienService.checkLogin(user, pass)){
                    idLog = user;
                    model.addAttribute("students", sinhVienService.getStudentById(user));
                    return "students__sv";
                } else{
                    return "index";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "index";

    }

    @GetMapping("/home")
    public String backToHome(Model model) throws SQLException {
        model.addAttribute("students", sinhVienService.getAllStudents());
        return "home";
      

    }

	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		// create student object to hold student form data
		SinhVien student = new SinhVien();
		model.addAttribute("student", student);
		return "create_student";
		
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") SinhVien student) {
		try {
            student.setId(sinhVienService.generateMssv());
            sinhVienService.saveStudent(student);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable String id, Model model) {
		try {
    
            model.addAttribute("student", sinhVienService.getStudentById(id));
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return "edit_student";
	}

	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable String id,
			@ModelAttribute("student") SinhVien student,
			Model model) {
		
		// get student from database by id
		SinhVien existingStudent;
        try {
            existingStudent = sinhVienService.getStudentById(id);
            existingStudent.setHoTen(student.getHoTen());
            existingStudent.setTuoi(student.getTuoi());
            existingStudent.setGioiTinh(student.isGioiTinh());
            existingStudent.setDiaChi(student.getDiaChi());
            existingStudent.setSdt(student.getSdt());
		    existingStudent.setEmail(student.getEmail());
            existingStudent.setLop(student.getLop());
            existingStudent.setGpa(Math.round(student.getGpa()*100.0)/100.0);
            sinhVienService.updateStudent(existingStudent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return "redirect:/students";
	}

    @GetMapping("/students__sv/edit/{id}")
	public String editForStudentForm(@PathVariable String id, Model model) {
		try {
            model.addAttribute("student", sinhVienService.getStudentById(id));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return "edit_student__sv";
	}

	@PostMapping("/students__sv/{id}")
	public String updateForStudent(@PathVariable String id,
			@ModelAttribute("student") SinhVien student,
			Model model) {
		
		// get student from database by id
		SinhVien existingStudent;
        try {
            existingStudent = sinhVienService.getStudentById(id);
            existingStudent.setHoTen(student.getHoTen());
            existingStudent.setTuoi(student.getTuoi());
            existingStudent.setGioiTinh(student.isGioiTinh());
            existingStudent.setDiaChi(student.getDiaChi());
            existingStudent.setSdt(student.getSdt());
		    existingStudent.setEmail(student.getEmail());
            existingStudent.setLop(student.getLop());
            existingStudent.setGpa(Math.round(student.getGpa()*100.0)/100.0);
            sinhVienService.updateStudent(existingStudent);
        } catch (SQLException e) {
          
            e.printStackTrace();
        }

		return "redirect:/students__sv";		
	}
    	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable String id) {
		sinhVienService.deleteStudentById(id);
		return "redirect:/students";
	}
    
    @GetMapping("/chang_pass")
	public String changPass() {
		return "changePass";
	}

    @PostMapping("/chang_pass")
    public String registerUserAccount(@RequestParam("user") String user, @RequestParam("pass") String pass, @RequestParam("newpass") String newpass, Model model) {
    
        try {
            boolean flag = sinhVienService.changPass(user, pass, newpass);
            if (flag == false) {
                model.addAttribute("errorMessage", "Tài khoản hoặc mật khẩu không chính xác!");
                return "changePass";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi đổi mật khẩu!");
            return "changePass";
        }
       
        model.addAttribute("successMessage", "Đổi mật khẩu thành công!");
        return "changePass";
    }

    @GetMapping("/students__sv")
    public String listStudentsForStudent(Model model) throws SQLException {
        model.addAttribute("students", sinhVienService.getStudentById(idLog));
        return "students__sv";
    }



}
