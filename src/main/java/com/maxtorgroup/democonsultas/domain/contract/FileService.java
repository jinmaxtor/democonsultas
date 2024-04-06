package com.maxtorgroup.democonsultas.domain.contract;
import com.maxtorgroup.democonsultas.domain.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    void init();
    void save(MultipartFile file);
    void delete(String fileName);
    void deleteAll();
    List<FileDto> loadAll();
}
