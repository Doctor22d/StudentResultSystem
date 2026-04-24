package com.example.uploadResult.service;

import com.example.uploadResult.model.StudentResult;
import com.example.uploadResult.repository.JpaRepo;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ResultService {
    private final JpaRepo jpaRepo;

    public String uploadFile(MultipartFile file) {
        boolean firstRow = true;
        List<StudentResult> results = new ArrayList<>();

        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (firstRow) {
                    firstRow = false;
                    continue;
                }
                Cell rollcell = row.getCell(0);
                Cell subjectCell = row.getCell(1);
                Cell gradeCell = row.getCell(2);
                Cell cgpaCell = row.getCell(3);
                if (rollcell == null) continue;
                String rollNo = rollcell.toString();
                String subject = subjectCell != null ? subjectCell.toString() : "";
                String grade = gradeCell != null ? gradeCell.toString() : "";
                String cgpa = cgpaCell != null ? cgpaCell.toString() : "";

                StudentResult studentResult = StudentResult.builder()
                        .cgpa(cgpa)
                        .grade(grade)
                        .subject(subject)
                        .rollNo(rollNo)
                        .build();
                results.add(studentResult);
            }
            jpaRepo.saveAll(results);
            return "Result Uploaded Successfully";
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<StudentResult> getResult(String rollNo) {
        return jpaRepo.findByRollNo(rollNo)
                .orElseThrow(() -> new RuntimeException("Result Not Found"));
    }
}
