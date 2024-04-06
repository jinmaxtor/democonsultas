package com.maxtorgroup.democonsultas.application.service;

import com.maxtorgroup.democonsultas.api.controller.MainController;
import com.maxtorgroup.democonsultas.application.exeption.FileException;
import com.maxtorgroup.democonsultas.domain.contract.FileService;
import com.maxtorgroup.democonsultas.domain.dto.FileDto;
import com.maxtorgroup.democonsultas.infrastructure.configuration.StorageProperties;
import lombok.Getter;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApplicationFileService implements FileService {

    @Getter
    private enum FileType {
        IMAGE("image"),
        DOCUMENT("document"),
        VIDEO("video"),
        AUDIO("audio"),
        OTHER("other");

        private final String type;

        FileType(String type) {
            this.type = type;
        }

        public String getFolderName() {
            return switch (this) {
                case IMAGE -> "images";
                case DOCUMENT -> "documents";
                case VIDEO -> "videos";
                case AUDIO -> "audios";
                default -> "others";
            };
        }

        public static FileType fromMimeType(String mimeType) {
            String type = mimeType.split("/")[0];

            return switch (type) {
                case "image" -> IMAGE;
                case "document" -> DOCUMENT;
                case "video" -> VIDEO;
                case "audio" -> AUDIO;
                default -> OTHER;
            };
        }

        public static FileType fromFileName(String fileName) {
            String type = URLConnection.guessContentTypeFromName(fileName);
            return fromMimeType(type);
        }
    }

    private final String baseUrl;
    private final Path rootLocation;

    public ApplicationFileService(StorageProperties properties) {
        if (properties == null) throw new IllegalArgumentException("Properties cannot be null");

        if (properties.getLocation() == null || properties.getLocation().trim().isEmpty()) {
            throw new FileException("File Location cannot be empty");
        }

        this.baseUrl = properties.getBaseUrl();
        this.rootLocation = Path.of(properties.getLocation());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new FileException("Could not initialize storage", e);
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            String path = FileType.fromMimeType(Objects.requireNonNull(file.getContentType())).getFolderName();

            if (file.isEmpty()) throw new FileException("File content cannot be empty");

            String fileName = Objects.requireNonNull(file.getOriginalFilename());
            Path targetPath = getFilePath(path, normalizeFileName(fileName));
            Files.copy(file.getInputStream(), targetPath);
        } catch (IOException e) {

            String fileName = file.getOriginalFilename();

            if (e instanceof FileAlreadyExistsException) {
                throw new FileException(String.format("File with name: %s already exists", fileName), e);
            }

            throw new FileException(String.format("Failed to store file with name: %s", fileName), e);
        }
    }

    @Override
    public void delete(String fileName) {
        try {
            String path = FileType.fromFileName(fileName).getFolderName();
            Path targetPath = getFilePath(path, fileName);
            validateDirectory(targetPath);
            Files.delete(targetPath);
        } catch (IOException e) {
            throw new FileException("Failed to delete file", e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(this.rootLocation.toFile());
    }

    @Override
    public List<FileDto> loadAll() {
        try {
            Stream<Path> pathStream;

            pathStream = Files.walk(this.rootLocation, 3)
                    .filter(path -> !path.equals(this.rootLocation) && !Files.isDirectory(path))
                    .map(this.rootLocation::relativize);

            return pathStream.map(this::toFileDto)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileException("Failed to read stored files", e);
        }
    }

    private Path getFilePath(String path, String fileName) throws IOException {
        Path targetPath = this.rootLocation
                .resolve(path)
                .normalize()
                .toAbsolutePath();

        if (!targetPath.startsWith(this.rootLocation.toAbsolutePath())) {
            throw new FileException("Cannot save file outside of the root directory");
        }

        Files.createDirectories(targetPath);

        targetPath = targetPath
                .resolve(fileName)
                .normalize()
                .toAbsolutePath();

        return targetPath;
    }

    private void validateDirectory(Path path) {
        if (!Files.isDirectory(path)) {
            throw new FileException("Cannot delete file from a directory");
        }
    }

    private FileDto toFileDto(Path path) {
        String baseUrl = MvcUriComponentsBuilder.fromMethodName(MainController.class, "index")
                .build() + this.baseUrl;

        String relativePath = path.toString().replaceAll("\\\\", "/");
        String url = String.format("%s/%s", baseUrl, relativePath);

        FileDto fileDto = new FileDto();
        fileDto.setName(path.getFileName().toString());
        fileDto.setPath(relativePath);
        fileDto.setUrl(url);
        return fileDto;
    }

    private String normalizeFileName(String fileName) {
        if (fileName.isEmpty()) {
            throw new FileException("File name cannot be empty");
        }

        int dotIndex = fileName.lastIndexOf(".");

        String extension = fileName.substring(dotIndex);
        fileName = fileName.substring(0, dotIndex);

        String normalizedName = Normalizer.normalize(fileName, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        normalizedName = pattern.matcher(normalizedName).replaceAll("");
        normalizedName = normalizedName.toLowerCase()
                .replaceAll("[^\\w\\s]", "-")
                .replaceAll("\\s+", "-");

        normalizedName = normalizedName.replaceAll("-+", "-");

        normalizedName = normalizedName
                .replaceAll("^-+", "")
                .replaceAll("-+$", "");

        if (normalizedName.length() > 255) {
            normalizedName = normalizedName.substring(0, 255);
        }

        return normalizedName + extension;
    }
}
