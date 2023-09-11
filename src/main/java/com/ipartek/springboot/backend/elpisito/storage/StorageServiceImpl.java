package com.ipartek.springboot.backend.elpisito.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ipartek.springboot.backend.elpisito.models.dao.IImagenDAO;
import com.ipartek.springboot.backend.elpisito.models.entity.Imagen;
import com.ipartek.springboot.backend.elpisito.models.entity.Inmueble;

import jakarta.annotation.PostConstruct;

@Service
public class StorageServiceImpl implements IStorageService{

	//Este objeto nos a permitir tener acceso a
	//todos los métodos de CrudRepository<Imagen,Long>
	
	@Autowired
	private IImagenDAO imagenDAO;
	
	//Estamos indicando que en esta propiedad definida
	//en el archivo "application-properties" está la ruta
	//en la que queremos albergar los archivos subidos...
	@Value("${media.location}")
	private String mediaLocation;
	
	//Este objeto de tipo Path representará la ruta raiz
	//de almacenamiente
	private Path rootLocation;
	
	
	@Override
	@PostConstruct //Esta notación ejecuta este método en el momento en que esta clase se instancia
	public void init() throws IOException {
		
		//Inicializamos la ruta raiz de almacenamiento
		rootLocation = Paths.get(mediaLocation);
		Files.createDirectories(rootLocation);
		
	}

	@Override
	public String store(MultipartFile file, Long idInmueble) {
		
		try {
		
				//Verificamos si el archivo no está vacío
				if(file.isEmpty()) {
					throw new RuntimeException ("No se puede almacenar un archivo vacío");
				}
				
				//habitacionpricipal948484.jpg
				//1-Vamos a conseguir la parte del tipo del archivo. Ejemplo: ".jpg"
				
				//El método getContentType nos devuelve el tipo MIME
				//https://developer.mozilla.org/es/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
				String fileContentType = file.getContentType();//"(image/jpeg)" "(image/jpg)"
				String tipo = "." + fileContentType.substring(fileContentType.lastIndexOf("/")+1); //".jpeg"
				
				if(tipo.equals(".jpeg")) {
					tipo=".jpg";
				}
				
				
				//SI DESEAMOS COMPLETAR-MATIZAR EL HECHO DE SUBIR OTROS TIPOS DE ARCHIVOS
				//...NO DEJAR SUBIR MAS QUE ARCHIVOS JPG...O CUALQUIER OTRO MATIZ DE "FILTRO"
				//DEBEMOS PROGRAMARLO AQUÍ...
				
				
				//2-Conseguimos el nombre del archivo
				String filename = String.valueOf(Calendar.getInstance().getTimeInMillis());
				filename = filename.concat(tipo);//95202025526458484884.jpg
				
				//3-Añadimos el string nombre del archivo a la ruta prefijada de destino 
				//de almacenamiento
				Path destinationFile = rootLocation.resolve(Paths.get(filename));
				
				//4-Movemos el archivo FÍSICAMENTE a su destino final
				
				//Esto es un try con recursos. Para utilizar un try con recursos
				//es necesario que las clases utilizadas dentro del paréntesis
				//implementen la interfaz "Autocloseable"
				try(InputStream inputStream = file.getInputStream()){
					
					//Si existe un archivo con el mismo nombre se reemplazará 
					//Lo vamos a almacenar utilizando la clase Files a partir de un inputStream
					//que maneja el archivo físico REAL
					Files.copy(inputStream,destinationFile,StandardCopyOption.REPLACE_EXISTING);
				}
				
				//5-Creamos la notación en la BBDD
				//////////////////////////////////////////////////////////////////
				//PREFIERO SUBIR PRIMERO LA IMAGEN Y POSTERIORMENTE CREAR LA
				//NOTACIÓN DE LA IMAGEN EN LA BBDD PORQUE SI LA IMAGEN NO SE CREA (ALGÚN ERROR)
				//SÓLO TENDRÉ COMO PARTE NEGATIVA UNA IMAGEN "FISICA" NO VINCULADA
				//DE LA OTRA FORMA TENDRÍA UN REGISTRO ERRÓNEO EN LA BBDD.
				//////////////////////////////////////////////////////////////////
				
				Imagen imagen = new Imagen();
				Inmueble inmueble = new Inmueble();
				inmueble.setId(idInmueble);
				imagen.setNombre(filename);
				imagen.setInmueble(inmueble);
				imagenDAO.save(imagen);
				
				///////////////////////////////////////////////////////////////////
				
				return filename;
				
				///////////////////////////////////////////////////////////////////
				
		}catch(IOException e) {
			
			throw new RuntimeException("Fallo al almacenar el archivo");
		}

		
		

	}

	@Override
	public Resource loadAsResource(String filename) {
		
		//Conseguimos el path REAL del archivo
		Path file = rootLocation.resolve(Paths.get(filename));
		try {
		
			Resource resource = new UrlResource(file.toUri());
			
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}else {
				
				throw new RuntimeException("No se puede leer el archivo " + filename);
			}
			
		} catch (MalformedURLException e) {
			throw new RuntimeException("No se puede leer el archivo " + filename);
		}
			
	}

}
