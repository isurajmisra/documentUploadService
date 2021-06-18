package com.ros.accounting.cashup.service.impl;

import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.ros.accounting.AzureStorageParam;
import com.ros.accounting.cashup.service.DocumentUploadService;
import com.ros.accounting.util.AzureStorageUtil;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@Service
public class DocumentServiceImpl implements DocumentUploadService {
    private static int thumbnailWidth = 150;
    private static int thumbnailHeight = 100;
    private static String thumbnailPrefix = "mini_";
    private static String originPrefix = "FAQ_";

    private final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

    private final Path root = Paths.get("uploads");

    @Value("{azure.storage.generate-thumbnail}")
    private String generateThumbnail;

    @Autowired
    private AzureStorageParam azureStorageParam;

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public String uploadFile(String type, MultipartFile[] multipartFiles) {
        // Check the picture
//        if (hasInvalidPic(multipartFiles)) {
//            return "Contains illegal image format";
//        }

//        List<BlobUpload> blobUploadEntities = new ArrayList<>();

        // Get the blob container
        CloudBlobContainer container = AzureStorageUtil.getAzureContainer(
                azureStorageParam.getStorageConnectionString(), azureStorageParam.getContainerReference());
        if (container == null) {
            logger.error("Abnormal acquisition of azure container");
//            return "Failed to get container";
        }
        try {
            for (MultipartFile tempMultipartFile : multipartFiles) {

                //upload locally
                try {
                    Files.copy(tempMultipartFile.getInputStream(), this.root.resolve(tempMultipartFile.getOriginalFilename()));
                    logger.info("File uploaded: "+ tempMultipartFile.getOriginalFilename());
                } catch (Exception e) {
                    throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
                }
//                try {
//                    // Upload the file to the container
//                    String contentType = tempMultipartFile.getContentType().toLowerCase();
//                    if (!contentType.equals("image/jpg") && !contentType.equals("image/jpeg")
//                            && !contentType.equals("image/png")) {
//                        return "not pic";
//                    }
//                    // Time + random number + file extension
//                    String picType = contentType.split("/")[1];
//                    String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//                    int number = (int) ((Math.random() * 9) * 1000);
//                    String referenceName = originPrefix + timeStamp + number + "." + picType;
//                    CloudBlockBlob blob = container.getBlockBlobReference(referenceName);
//                    blob.getProperties().setContentType(tempMultipartFile.getContentType());
//                    blob.upload(tempMultipartFile.getInputStream(), tempMultipartFile.getSize());
//                    logger.info("File Url: "+ blob.getUri().toString());
//
//                    // return image URL
////                    BlobUpload blobUploadEntity = new BlobUpload();
////                    blobUploadEntity.setFileName(tempMultipartFile.getOriginalFilename())
////                            .setFileUrl(blob.getUri().toString());
//
//                    // Generate thumbnail
//                    if ("true".equalsIgnoreCase(generateThumbnail)) {
//                        BufferedImage img =
//                                new BufferedImage(thumbnailWidth, thumbnailHeight, BufferedImage.TYPE_INT_RGB);
//                        BufferedImage read = ImageIO.read(tempMultipartFile.getInputStream());
//                        img.createGraphics().drawImage(
//                                read.getScaledInstance(thumbnailWidth, thumbnailHeight, Image.SCALE_SMOOTH), 0, 0, null);
//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        ImageIO.write(img, "jpg", baos);
//                        InputStream bais = new ByteArrayInputStream(baos.toByteArray());
//
//                        String blobThumbnail = originPrefix + thumbnailPrefix + timeStamp + number + ".jpg";
//                        CloudBlockBlob thumbnailBlob = container.getBlockBlobReference(blobThumbnail);
//                        thumbnailBlob.getProperties().setContentType("image/jpeg");
//                        thumbnailBlob.upload(bais, baos.toByteArray().length);
//
////                        blobUploadEntity.setFileUrl(blob.getUri().toString())
////                                .setThumbnailUrl(thumbnailBlob.getUri().toString());
//                        logger.info("Thumbnail Url: " + thumbnailBlob.getUri().toString());
//                        // close the stream
//                        baos.close();
//                        bais.close();
//                    }
////                    blobUploadEntities.add(blobUploadEntity);
//                } catch (Exception e) {
//                    logger.error("An exception occurred while uploading [{}]: [{}]", tempMultipartFile.getOriginalFilename(), e.getMessage());
//                    return "An exception occurred while uploading, please try again later";
//                }
            }
            return "Uploaded";
        } catch (Exception e) {
            logger.error("Unexpected upload file: [{}]", e.getMessage());
        }
        return "An exception occurred while uploading, please try again later";
    }

//    private boolean hasInvalidPic(MultipartFile[] multipartFiles) {
//        List<String> picTypeList = Arrays.asList("image/jpg", "image/jpeg", "image/png");
//        return Arrays.stream(multipartFiles).anyMatch(i -> !picTypeList.contains(i.getContentType().toLowerCase()));
//    }
}