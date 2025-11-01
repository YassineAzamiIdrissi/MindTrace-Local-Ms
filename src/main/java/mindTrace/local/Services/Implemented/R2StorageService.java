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
                "21255d760b172c003e065f11b7eae6b8", "3c878b02b71ae73f9383614bec8703a42e98439258f4061b185b6e7029d00f14"
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

        return "https://pub-b3da32dbe5434b10864bdd1d48e8629a.r2.dev/" + filename;
    }

}