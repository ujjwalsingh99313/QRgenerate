package com.springboot.qrcodegen.controller;

import com.google.zxing.WriterException;
import com.springboot.qrcodegen.entity.Student;
import com.springboot.qrcodegen.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.springboot.qrcodegen.repo.StudentRepository;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Optional;

@Controller
public class QrController {
    @Autowired
    private QRCodeService qrCodeService;


    @Autowired
    private StudentRepository studentRepository;



    @GetMapping("/")
    public String home() {
        return "home";
    }


    @GetMapping("/student")
    public String getStudent(@RequestParam("rollNo") String rollNo, Model model) {
        Optional<Student> studentOpt = studentRepository.findByRollNo(rollNo);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            model.addAttribute("student", student);
        }
       /* List<Student> stdList = new ArrayList<>();
        Student sdt1 = new Student(1l,"S01","Ujjwal Kumar","ujjwal@gmail.com");
        Student sdt2 = new Student(2l,"S02","Rohit Keshri","keshri@gmail.com");
        Student sdt3 = new Student(3l,"S03","Aditiya Neha","nehaAdi@gmail.com");
        Student sdt4 = new Student(4l,"S04","Manjeet","man@gmail.com");

        stdList.add(sdt1);
        stdList.add(sdt2);
        stdList.add(sdt3);
        stdList.add(sdt4);

        for(Student std: stdList){
            if(std.getRollNo().equalsIgnoreCase(rollNo)){
                model.addAttribute("student", std);
            }
        }*/

        return "student";
    }

    @GetMapping("/qrcode")
    @ResponseBody
    public void getQRCode(@RequestParam("rollNo") String rollNo, HttpServletResponse response) throws WriterException, IOException {
        /*List<Student> stdList = new ArrayList<>();
        Student sdt1 = new Student(1l,"S01","Ujjwal Kumar","ujjwal@gmail.com");
        Student sdt2 = new Student(2l,"S02","Rohit Keshri","keshri@gmail.com");
        Student sdt3 = new Student(3l,"S03","Aditiya Neha","nehaAdi@gmail.com");
        Student sdt4 = new Student(4l,"S04","Manjeet Lodu","ManLodu@gmail.com");

        stdList.add(sdt1);
        stdList.add(sdt2);
        stdList.add(sdt3);
        stdList.add(sdt4);

        for(Student student: stdList){
            if(student.getRollNo().equalsIgnoreCase(rollNo)){
                String qrText = "Name: " + student.getName() + "\nEmail: " + student.getEmail();
                byte[] qrCodeImage = qrCodeService.generateQRCodeWithLogo(qrText, "/static/logo.png");

                response.setContentType("image/png");
                InputStream inputStream = new ByteArrayInputStream(qrCodeImage);
                ImageIO.write(ImageIO.read(inputStream), "PNG", response.getOutputStream());
            }
        }*/

        Optional<Student> studentOpt = studentRepository.findByRollNo(rollNo);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            String qrText = "Name: " + student.getName() + "\nRoll No: "+student.getRollNo()
                    +"\nEmail: " + student.getEmail()
                    +"\nPaper1 Marks: "+student.getPaper1Marks()
                    +"\nPaper2 Marks: "+student.getPaper2Marks();
            byte[] qrCodeImage = qrCodeService.generateQRCodeWithLogo(qrText, "/static/logo.png");

            response.setContentType("image/png");
            InputStream inputStream = new ByteArrayInputStream(qrCodeImage);
            ImageIO.write(ImageIO.read(inputStream), "PNG", response.getOutputStream());
        }
    }
}
