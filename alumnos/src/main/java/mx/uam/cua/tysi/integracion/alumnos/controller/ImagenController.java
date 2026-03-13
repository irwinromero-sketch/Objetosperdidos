package mx.uam.cua.tysi.integracion.alumnos.controller;

import mx.uam.cua.tysi.integracion.alumnos.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/imagenes")
public class ImagenController {

    private final FileStorageService fileStorageService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ImagenController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/subir")
    public String subirImagen(@RequestParam("archivo") MultipartFile archivo,
                              @RequestParam("nombre") String nombre) {
        String nombreGuardado = fileStorageService.guardarArchivo(archivo, nombre);
        return "Imagen guardada como: " + nombreGuardado +
                " — Ver en: http://localhost:8080/imagenes/" + nombreGuardado;
    }

    @GetMapping("/{nombreArchivo}")
    public ResponseEntity<Resource> verImagen(@PathVariable String nombreArchivo) {
        try {
            Path filePath = Paths.get(uploadDir).toAbsolutePath().resolve(nombreArchivo);
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            String contentType = "image/jpeg";
            if (nombreArchivo.endsWith(".png")) contentType = "image/png";
            if (nombreArchivo.endsWith(".gif")) contentType = "image/gif";

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + nombreArchivo + "\"")
                    .body(resource);

        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}