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
    private final String bucketName = ".....";

    public R2StorageService() {
        AwsBasicCredentials creds = AwsBasicCredentials.create(
                "2....", "3c......"
        );

        this.s3 = S3Client.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(java.net.URI.create("https://c9c023c115897be3334426712ad01e2a.r2.cloudflarestorage.com"))
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

        return "https://.../" + filename;
    }

}
