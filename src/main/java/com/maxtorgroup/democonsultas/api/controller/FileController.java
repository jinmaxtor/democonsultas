package com.maxtorgroup.democonsultas.api.controller;

import com.maxtorgroup.democonsultas.domain.contract.FileService;
import com.maxtorgroup.democonsultas.domain.dto.FileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public ResponseEntity<List<FileDto>> getListUploadedFiles() {
        return ResponseEntity.ok(fileService.loadAll());
    }

    @PostMapping("/upload")
    public ResponseEntity<Boolean> singleUpload(@RequestParam("file") MultipartFile file) {
        try {
            fileService.save(file);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }
}
