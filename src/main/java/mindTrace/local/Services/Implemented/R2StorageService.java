package mindTrace.local.Services.Implemented;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class R2StorageService {
    private final S3Client s3;
    private final String bucketName = "my-test-bucket";

    public R2StorageService() {
        AwsBasicCredentials creds = AwsBasicCredentials.create(
                "<ACCESS_KEY_ID>", "<SECRET_ACCESS_KEY>"
        );

        this.s3 = S3Client.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(java.net.URI.create("https://<ACCOUNT_ID>.r2.cloudflarestorage.com"))
                .credentialsProvider(StaticCredentialsProvider.create(creds))
                .build();
    }

    public String uploadFile(String filename, byte[] fileBytes, String contentType) {
        s3.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(filename)
                        .contentType(contentType)
                        .build(),
                software.amazon.awssdk.core.sync.RequestBody.fromBytes(fileBytes)
        );

        return "https://" + bucketName + ".<ACCOUNT_ID>.r2.cloudflarestorage.com/" + filename;
    }

}
