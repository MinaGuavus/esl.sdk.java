package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Audit;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.io.Files;

import java.util.List;
import java.util.Properties;

/**
 * Downloads a document, the evidence summary, and the documents zip file
 */
public class DownloadDocumentsEvidenceAndAuditExample {

    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    public static void main( String... args ) {
        EslClient esl = new EslClient( API_KEY, API_URL );

        PackageId packageId = new PackageId("your package id");
        byte[] documentContent = esl.downloadDocument(packageId, "your document id");

        Files.saveTo(documentContent, "downloaded.pdf");

        byte[] evidenceContent = esl.downloadEvidenceSummary(packageId);
        Files.saveTo(evidenceContent, "evidence.pdf");

        byte[] zip = esl.downloadZippedDocuments(packageId);
        Files.saveTo(zip, "package.zip");

        List<Audit> auditList = esl.getAuditService().getAudit( packageId );

        System.out.println();
    }
}