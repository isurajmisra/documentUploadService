package com.ros.accounting.cashup.service;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentUploadService {
    public void init();
    String uploadFile(String type, MultipartFile[] multipartFiles);
}
