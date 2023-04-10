package com.matrimony.codewithnitin.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.matrimony.codewithnitin.entity.ImageData;
import com.matrimony.codewithnitin.entity.Profile;
import com.matrimony.codewithnitin.entity.ProfilePicture;
import com.matrimony.codewithnitin.payload.ImageUtils;
import com.matrimony.codewithnitin.repository.ProfilePictureRepository;
import com.matrimony.codewithnitin.repository.StorageRepository;

@Service
public class StorageService {

	@Autowired
	private StorageRepository storageRepository;

	@Autowired
	private ProfilePictureRepository profilePictureRepository;

	public String uploadImage(MultipartFile file) throws java.io.IOException {

		ImageData imageData = storageRepository.save(ImageData.builder().name(file.getOriginalFilename())
				.type(file.getContentType()).imageData(ImageUtils.compressImage(file.getBytes())).build());

		if (imageData != null) {
			return "file uploaded successfully : " + file.getOriginalFilename();
		}
		return null;
	}



	private final String FOLDER_PATH = "C:/Users/kolte/OneDrive/Desktop/myfiles/";

	public String uploadImageToFileSystem(MultipartFile file ,Integer profileId ) throws IOException {

		String imageFilePath = FOLDER_PATH + file.getOriginalFilename();
		ProfilePicture profilePicture = ProfilePicture.builder()
			    .imageFileName(file.getOriginalFilename())
			    .type(file.getContentType())
			    .imageFilePath(imageFilePath)
			    .profile(Profile.builder().profileId(profileId).build())
			    .build();
			profilePicture = profilePictureRepository.save(profilePicture);

		file.transferTo(new File(imageFilePath));

		if (profilePicture != null) {
			return "file uploaded Successfully :" + imageFilePath;

		}
		return null;
	}

//	public byte[] downloadImageFromMyFileSystem(String fileName) throws IOException {
//		Optional<ProfilePicture> profilePicture = profilePictureRepository.findByimageFileName(fileName);
//		String imageFilePath = profilePicture.get().getImageFilePath();
//		byte[] images = Files.readAllBytes(new File(imageFilePath).toPath());
//		return images;
//	}

//	public byte[] downloadImageFromMyFileSystem(int profileId) throws IOException {
//	    Optional<ProfilePicture> profilePicture = profilePictureRepository.findByProfile_ProfileId(profileId);
//	    if (!profilePicture.isPresent()) {
//	        throw new RuntimeException("No image found for profile ID: " + profileId);
//	    }
//	    String imageFilePath = profilePicture.get().getImageFilePath();
//	    byte[] images = Files.readAllBytes(new File(imageFilePath).toPath());
//	    return images;
//	}





	// download image 
	public Map<String, String> downloadImageFromMyFileSystem(int profileId) throws IOException {
	    Optional<ProfilePicture> profilePicture = profilePictureRepository.findByProfile_ProfileId(profileId);
	    if (!profilePicture.isPresent()) {
	        throw new RuntimeException("No image found for profile ID: " + profileId);
	    }
	    String imageName = profilePicture.get().getImageFileName();
	    String imagePath = profilePicture.get().getImageFilePath();
	  
	    //  String imageURL = "http://yourdomain.com/showimage/" + profileId;
	    
	    Map<String, String> response = new HashMap<>();
	    response.put("imagePath", imagePath);
	    //  response.put("imageURL", imageURL);
	    return response;
	}


}
