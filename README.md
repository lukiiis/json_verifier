# JSON Verifier
AWS::IAM::Role Policy verifier in Java.

## Installation
Clone this project to your computer, open terminal and go to the root directory of the project and run `mvn clean test`. You can also run the `Verifier` Java class to run the main function, which will run the program.

## Functionality

1. **`verifyJson(String path)`**:
    - This method reads a JSON file from the given path and checks its correctness according to IAM role policy.
    - If the file is not a valid IAM role policy, the program displays an error message.
    - It then checks each statement contained within the policy.

2. **`verifyResource(JsonNode statement)`**:
    - This method verifies the correctness of the resource in a given statement.
    - If the resource is set to a single asterisk ("*"), the function returns `false`; otherwise, it returns `true`.
    - If the statement does not contain resource field, it returns false.

3. **`verifyFile(JsonNode rootNode)`**:
    - This method verifies if the file contain `PolicyDocument` field and `Statement` field.
    - If the file does not contain these fields, it returns false, which indicates that the file is incorrect.
    - If the file contain these fields, it returns true, which indicates, that file can be further processed.

## Test Cases

1. **`testVerifyJsonWithMultipleStatements()`**
    - **Description**: Verifies the functionality of the `verifyJson(String path)` method when the JSON file contains multiple statements in the IAM role policy.
    - **Expected Outcome**: The method should execute without errors, indicating successful verification of the policy.

2. **`testVerifyJsonWithFalseResource()`**
    - **Description**: Tests the behavior of the `verifyJson(String path)` method when the JSON file contains a statement with a false resource.
    - **Expected Outcome**: The method should properly identify the false resource and handle it according to the verification logic.

3. **`testVerifyJsonWithTrueResource()`**
    - **Description**: Checks the functionality of the `verifyJson(String path)` method when the JSON file contains a statement with a true resource.
    - **Expected Outcome**: The method should correctly identify the true resource and pass the verification process.

4. **`testVerifyResourceWithValidResource()`**
    - **Description**: Validates the `verifyResource(JsonNode statement)` method's behavior when provided with a valid resource in a JSON node.
    - **Expected Outcome**: The method should return `true`, indicating that the resource is valid according to the verification criteria.

5. **`testVerifyResourceWithNullResource()`**
    - **Description**: Tests the `verifyResource(JsonNode statement)` method's handling of a JSON node with a null resource.
    - **Expected Outcome**: The method should return `false`, as a null resource does not meet the verification criteria.

6. **`testVerifyResourceWithWildcardResource()`**
    - **Description**: Verifies the behavior of the `verifyResource(JsonNode statement)` method when provided with a wildcard resource in a JSON node.
    - **Expected Outcome**: The method should return `false`, as a wildcard resource is considered invalid according to the verification criteria.

7. **`testVerifyFileWithValidPolicyDocument()`**
    - **Description**: Checks the functionality of the `verifyFile(JsonNode rootNode)` method when provided with a valid IAM policy document in a JSON node.
    - **Expected Outcome**: The method should return `true`, indicating that the policy document is valid.

8. **`testVerifyFileWithNullPolicyDocument()`**
    - **Description**: Tests the `verifyFile(JsonNode rootNode)` method's handling of a JSON node with a null policy document.
    - **Expected Outcome**: The method should return `false`, as a null policy document does not meet the verification criteria.

9. **`testVerifyFileWithNullStatement()`**
    - **Description**: Validates the behavior of the `verifyFile(JsonNode rootNode)` method when provided with a JSON node with a null statement.
    - **Expected Outcome**: The method should return `false`, as a null statement does not meet the verification criteria.

## Notes
`resources` directory contains multiple policies, required for manual testing.