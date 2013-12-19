package com.silanis.esl.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.silanis.esl.api.model.PackageReminderSchedule;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.ReminderSchedule;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.internal.converter.ReminderScheduleConverter;

public class ReminderService {
    private final UrlTemplate template;
    private final RestClient client;

    public ReminderService( RestClient client, String baseUrl ) {
        template = new UrlTemplate( baseUrl );
        this.client = client;
    }

    public ReminderSchedule getReminderScheduleForPackage( PackageId packageId ) {
        String path = template.urlFor( UrlTemplate.REMINDERS_PATH ).replace( "{packageId}", packageId.getId() ).build();
        try {
            String stringResponse = client.get( path );
            PackageReminderSchedule apiReminderSchedule = JacksonUtil.deserialize( stringResponse, new TypeReference<PackageReminderSchedule>() {
            } );
            ReminderSchedule sdkReminder = new ReminderScheduleConverter( apiReminderSchedule ).toSDKReminderSchedule();
            return sdkReminder;
        } catch ( Exception e ) {
            throw new EslException( "Failed to retrieve reminder.", e );
        }
    }

    public ReminderSchedule setReminderScheduleForPackage( ReminderSchedule reminderSchedule ) {
        String path = template.urlFor( UrlTemplate.REMINDERS_PATH ).replace( "{packageId}", reminderSchedule.getPackageId().getId() ).build();
        PackageReminderSchedule apiReminderSchedule = new ReminderScheduleConverter( reminderSchedule ).toAPIPackageReminderSchedule();
        try {
            String stringResponse = client.post( path, Serialization.toJson( apiReminderSchedule ) );
            PackageReminderSchedule apiResponse = Serialization.fromJson( stringResponse, PackageReminderSchedule.class );
            return new ReminderScheduleConverter( apiResponse ).toSDKReminderSchedule();
        } catch ( Exception e ) {
            throw new EslException( "Unable to create a new reminder.", e );
        }
    }

    public void clearReminderScheduleForPackage( PackageId packageId ) {
        String path = template.urlFor( UrlTemplate.REMINDERS_PATH ).replace( "{packageId}", packageId.getId() ).build();
        try {
            client.delete( path );
        } catch ( Exception e ) {
            throw new EslException( "Unable to clear reminder schedule for package with ID: " + packageId.getId() );
        }
    }
}
