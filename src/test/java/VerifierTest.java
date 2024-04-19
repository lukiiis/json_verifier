import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.Verifier;
import java.io.IOException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VerifierTest {
    private Verifier verifier;

    @BeforeEach
    public void setUp() {
        verifier = new Verifier();
    }

    @Test
    public void testVerifyJsonWithMultipleStatements() {
        String filePath = "src/main/resources/testMultiple.json";
        verifier.verifyJson(filePath);
    }

    @Test
    public void testVerifyJsonWithFalseResource() {
        String filePath = "src/main/resources/testFalse.json";
        verifier.verifyJson(filePath);
    }

    @Test
    public void testVerifyJsonWithTrueResource() {
        String filePath = "src/main/resources/testTrue.json";
        verifier.verifyJson(filePath);
    }

    @Test
    public void testVerifyResourceWithValidResource() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree("{\"Resource\": \"arn:aws:s3:::*\"}");
            assertTrue(verifier.verifyResource(rootNode));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testVerifyResourceWithNullResource() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree("{}");
            assertFalse(verifier.verifyResource(rootNode));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testVerifyResourceWithWildcardResource() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree("{\"Resource\": \"*\"}");
            assertFalse(verifier.verifyResource(rootNode));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testVerifyFileWithValidPolicyDocument() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree("{\"PolicyDocument\": {\"Statement\": []}}");
            assertTrue(verifier.verifyFile(rootNode));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testVerifyFileWithNullPolicyDocument() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree("{}");
            assertFalse(verifier.verifyFile(rootNode));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testVerifyFileWithNullStatement() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree("{\"PolicyDocument\": {}}");
            assertFalse(verifier.verifyFile(rootNode));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
