package com.ros.accounting;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AzureStorageParam {

    @Value("${azure.storage.default-endpoints-protocol}")
    private String defaultEndpointsProtocol;

    @Value("${azure.storage.account-name}")
    private String accountName;

    @Value("${azure.storage.account-key}")
    private String accountKey;

    @Value("${azure.storage.endpoint-suffix}")
    private String endpointSuffix;

    @Value("${azure.storage.container-reference}")
    private String containerReference;

    /**
     * Concatenation string
     */
    public String getStorageConnectionString() {
        String storageConnectionString =
                String.format("DefaultEndpointsProtocol=%s;AccountName=%s;AccountKey=%s;EndpointSuffix=%s",
                        defaultEndpointsProtocol, accountName, accountKey, endpointSuffix);
        return storageConnectionString;
    }

    public String getContainerReference() {
        return containerReference;
    }
}