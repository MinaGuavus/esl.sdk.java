package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.PackageId;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by lena on 2014-05-02.
 */
public class CreatePackageFromTemplateExampleTest {
    @Test
    public void verifyResult() {
        CreatePackageFromTemplateExample2 example = new CreatePackageFromTemplateExample2(Props.get());
        example.run();

        PackageId retrievedPackageId = example.getRetrievedPackageId();

        DocumentPackage retrievedPackage = example.eslClient.getPackageService().getPackage(retrievedPackageId);
        Document document = retrievedPackage.getDocument(example.DOCUMENT_NAME);

        assertThat("Document name is incorrectly returned.", document.getName().toString(), is(example.DOCUMENT_NAME));
        assertThat("Document ID is incorrectly returned.", document.getId().toString(), is(example.DOCUMENT_ID));

        assertThat("Package name is incorrectly returned.", retrievedPackage.getName(), is(example.PACKAGE_NAME));
        assertThat("Package description is incorrectly returned.", retrievedPackage.getDescription(), is(example.PACKAGE_DESCRIPTION));
        assertThat("Package email message is incorrectly returned.", retrievedPackage.getPackageMessage(), is(example.PACKAGE_EMAIL_MESSAGE2));

        assertThat("Number of package signers is incorrectly returned.", retrievedPackage.getSigners().size(), is(3));
        assertThat("Package signer 1 first name is incorrectly returned.", retrievedPackage.getSigner(example.getEmail1()).getFirstName(), is(example.PACKAGE_SIGNER1_FIRST));
        assertThat("Package signer 1 last name is incorrectly returned.", retrievedPackage.getSigner(example.getEmail1()).getLastName(), is(example.PACKAGE_SIGNER1_LAST));
        assertThat("Package signer 2 first name is incorrectly returned.", retrievedPackage.getSigner(example.getEmail2()).getFirstName(), is(example.PACKAGE_SIGNER2_FIRST));
        assertThat("Package signer 2 last name is incorrectly returned.", retrievedPackage.getSigner(example.getEmail2()).getLastName(), is(example.PACKAGE_SIGNER2_LAST));

        //TODO: Make sure that this is correctly preserved.
//        assertThat("Package settings.inPerson incorrectly returned.", retrievedPackage.getSettings().getEnableInPerson(), is(false));
        assertThat("Package settings.enableDecline is incorrectly returned.", retrievedPackage.getSettings().getEnableDecline(), is(false));
        assertThat("Package settings.enableOptOut incorrectly returned.", retrievedPackage.getSettings().getEnableOptOut(), is(false));
        assertThat("Package settings.hideWatermark incorrectly returned.", retrievedPackage.getSettings().getHideWatermark(), is(false));
    }
}
