package com.ipartek.springboot.backend.elpisito.storage;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;



public interface IStorageService {
	
	//Método auxiliar para preparar todo lo necesario
	//para la subida de archivos
	void init() throws IOException;
	
	//Con este método almacenaremos FÍSICAMENTE 
	//el archivo en la carpeta de destino
	String store(MultipartFile file, Long idInmueble);
	
	
	Resource loadAsResource(String filename);

}
