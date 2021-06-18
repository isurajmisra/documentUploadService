package com.ros.accounting.util;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Data
public class AzureStorageUtil {
    private static final Logger log = LogManager.getLogger(AzureStorageUtil.class);
    /**
     * Get blob container
     *
     * @param storageConnectionString
     * @param containerReference
     * @return
     */
    public static CloudBlobContainer getAzureContainer(String storageConnectionString, String containerReference) {
        CloudStorageAccount storageAccount;
        CloudBlobClient blobClient = null;
        CloudBlobContainer container = null;
        try {
            storageAccount = CloudStorageAccount.parse(storageConnectionString);
            blobClient = storageAccount.createCloudBlobClient();
            container = blobClient.getContainerReference(containerReference);

            container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(),
                    new OperationContext());
            return container;
        } catch (Exception e) {
            log.error("Abnormal access to azure container: [{}]" , e.getMessage());
        }
        return null;
    }
}
