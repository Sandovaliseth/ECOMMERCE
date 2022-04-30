package com.ecommerce.models.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
	
	private String folder="imagenes//";
	
	public String saveImagen(MultipartFile file) throws IOException {
		if(file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(folder+file.getOriginalFilename());
			Files.write(path, bytes);
			return file.getOriginalFilename();
		}
		return "";
	}
	
	public void deleteImagen(String nombre) {
		String ruta = "imagenes//";
		File file = new File(ruta+nombre);
		file.delete();
	}
}
