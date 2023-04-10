
package com.matrimony.codewithnitin.config.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.matrimony.codewithnitin.entity.ImageData;
import com.matrimony.codewithnitin.entity.ProfilePicture;
import com.matrimony.codewithnitin.payload.ImageUtils;
import com.matrimony.codewithnitin.repository.ProfilePictureRepository;
import com.matrimony.codewithnitin.repository.StorageRepository;
import com.matrimony.codewithnitin.service.StorageService;

@RestController
@RequestMapping("/api")
public class ImageController {

	@Autowired
	private StorageService service;
	
	@Autowired
	StorageRepository storageRepository;

	@Autowired
	private ProfilePictureRepository profilePictureRepository;

	// upload image and store path

//	@PostMapping("/uploadimage")
//	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
//		String uploadImage = service.uploadImage(file);
//		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
//	}

	
	//upload image as file
	@PostMapping("/uploadimagefile")  
	public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image") MultipartFile file,
			@RequestParam("profileId") Integer profileId) throws IOException {
		String uploadImage = service.uploadImageToFileSystem(file, profileId);
		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
	}

	public String uploadImage(MultipartFile file) throws java.io.IOException {
		ImageData imageData = storageRepository.save(ImageData.builder().name(file.getOriginalFilename())
				.type(file.getContentType()).imageData(ImageUtils.compressImage(file.getBytes())).build());
		if (imageData != null) {
			return "file uploaded successfully : " + file.getOriginalFilename();
		}
		return null;
	}
	


	
	//get image response as url 
	@GetMapping( "/showimage/{profileid}")
	@ResponseBody
	public ResponseEntity<Map<String, String>> showImageByProfileId(@PathVariable Integer profileid) throws IOException {
	    Map<String, String> imageInfo = service.downloadImageFromMyFileSystem(profileid);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    return ResponseEntity.ok().headers(headers).body(imageInfo);
	}

	
	
	//image as file 
//	@GetMapping(value = "/showimage/{profileid}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
//	@ResponseBody
//	public ResponseEntity<Resource> showImageByProfileId(@PathVariable Integer profileid) throws IOException {
//		 byte[] imageBytes = service.downloadImageFromMyFileSystem(profileid);
//		    ByteArrayResource resource = new ByteArrayResource(imageBytes);
//		    HttpHeaders headers = new HttpHeaders();
//		    headers.setContentType(MediaType.IMAGE_JPEG);
//		    headers.setContentLength(imageBytes.length);
//		    headers.setContentDispositionFormData(Integer.toString(profileid), Integer.toString(profileid));
//		    return ResponseEntity.ok().headers(headers).body(resource);
//		}
	
	
	

	
	 
	
	
//	@PostMapping("/uploadimage")
//	public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("profileId") Integer profileId, @RequestParam("file") MultipartFile file) throws IOException {
//	    String originalFilename = file.getOriginalFilename();
//	    String contentType = file.getContentType();
//
//	    String imageFilePath = service.uploadImageToFileSystem(file, profileId);
//	    if (imageFilePath != null) {
//	        return ResponseEntity.status(HttpStatus.OK).body(imageFilePath);
//	    } else {
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file.");
//	    }
//	}


}
