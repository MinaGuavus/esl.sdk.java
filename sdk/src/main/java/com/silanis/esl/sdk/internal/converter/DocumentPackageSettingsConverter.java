package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.*;
import com.silanis.esl.sdk.DocumentPackageSettings;
import com.silanis.esl.sdk.builder.CeremonyLayoutSettingsBuilder;

/**
 * User: jessica
 * Date: 27/11/13
 * Time: 3:12 PM
 *
 * Converter between SDK and API package settings.
 */

public class DocumentPackageSettingsConverter {
    private com.silanis.esl.sdk.DocumentPackageSettings sdkPackageSettings = null;
    private com.silanis.esl.api.model.PackageSettings apiPackageSettings = null;

    /**
     * Construct with API package settings object involved in conversion.
     *
     * @param apiPackageSettings
     */
    public DocumentPackageSettingsConverter(com.silanis.esl.api.model.PackageSettings apiPackageSettings) {
        this.apiPackageSettings = apiPackageSettings;
    }

    /**
     * Construct with SDK package settings object involved in conversion.
     * @param sdkPackageSettings
     */
    public DocumentPackageSettingsConverter(com.silanis.esl.sdk.DocumentPackageSettings sdkPackageSettings) {
        this.sdkPackageSettings = sdkPackageSettings;
    }

    /**
     * Convert from SDK package settings to API package settings.
     *
     * @return an API package settings object.
     */
    public com.silanis.esl.api.model.PackageSettings toAPIPackageSettings() {
        if (sdkPackageSettings == null) {
            return apiPackageSettings;
        }

        CeremonySettings ceremonySettings = new CeremonySettings();

        ceremonySettings.safeSetInPerson( sdkPackageSettings.getEnableInPerson() );
        ceremonySettings.safeSetOptOutButton( sdkPackageSettings.getEnableOptOut() );
        ceremonySettings.safeSetDeclineButton( sdkPackageSettings.getEnableDecline() );
        ceremonySettings.safeSetHideWatermark( sdkPackageSettings.getHideWatermark() );
        ceremonySettings.safeSetHideCaptureText( sdkPackageSettings.getHideCaptureText() );
        ceremonySettings.safeSetOptOutReasons( sdkPackageSettings.getOptOutReasons() );
        ceremonySettings.safeSetMaxAuthFailsAllowed( sdkPackageSettings.getMaxAuthAttempts() );

        if ( sdkPackageSettings.getLinkHref() != null ) {
            Link link = new Link();
            link.setHref( sdkPackageSettings.getLinkHref() );
            link.setText( sdkPackageSettings.getLinkText() == null ? sdkPackageSettings.getLinkHref() : sdkPackageSettings.getLinkText() );
            link.setTitle( sdkPackageSettings.getLinkTooltip() == null ? sdkPackageSettings.getLinkHref() : sdkPackageSettings.getLinkTooltip() );
            ceremonySettings.setHandOver( link );
        }

        if ( sdkPackageSettings.getShowDialogOnComplete() != null ) {
            CeremonyEvents ceremonyEvents = new CeremonyEvents();
            CeremonyEventComplete ceremonyEventComplete = new CeremonyEventComplete();
            ceremonyEventComplete.setDialog( sdkPackageSettings.getShowDialogOnComplete() );
            ceremonyEvents.setComplete( ceremonyEventComplete );
            ceremonySettings.setEvents(ceremonyEvents);
        }

        if ( sdkPackageSettings.getShowDocumentToolbarDownloadButton() != null ) {
            DocumentToolbarOptions documentToolbarOptions = new DocumentToolbarOptions();
            documentToolbarOptions.setDownloadButton( sdkPackageSettings.getShowDocumentToolbarDownloadButton() );
            ceremonySettings.setDocumentToolbarOptions( documentToolbarOptions );
        }

        if ( sdkPackageSettings.getCeremonyLayoutSettings() != null ) {
            ceremonySettings.setLayout(new CeremonyLayoutSettingsConverter(sdkPackageSettings.getCeremonyLayoutSettings()).toAPILayoutOptions());
        }

        PackageSettings result = new PackageSettings();
        result.setCeremony( ceremonySettings );

        return result;

    }

    /**
     * Convert from API package settings to SDK package settings.
     *
     * @return an SDK package settings object.
     */
    public com.silanis.esl.sdk.DocumentPackageSettings toSDKPackageSettings() {

        if (apiPackageSettings == null) {
            return sdkPackageSettings;
        }

        com.silanis.esl.sdk.DocumentPackageSettings result = new DocumentPackageSettings();
        result.setEnableInPerson(apiPackageSettings.getCeremony().getInPerson());
        result.setEnableOptOut(apiPackageSettings.getCeremony().getOptOutButton());
        result.setEnableDecline(apiPackageSettings.getCeremony().getDeclineButton());
        result.setHideWatermark(apiPackageSettings.getCeremony().getHideWatermark());
        result.setHideCaptureText(apiPackageSettings.getCeremony().getHideCaptureText());
        result.getOptOutReasons().addAll(apiPackageSettings.getCeremony().getOptOutReasons());
        result.setMaxAuthAttempts(apiPackageSettings.getCeremony().getMaxAuthFailsAllowed());

        if (apiPackageSettings.getCeremony().getDocumentToolbarOptions() != null)
            result.setShowDocumentToolbarDownloadButton(apiPackageSettings.getCeremony().getDocumentToolbarOptions().getDownloadButton());

        if (apiPackageSettings.getCeremony().getEvents() != null &&
                apiPackageSettings.getCeremony().getEvents().getComplete() != null)
            result.setShowDialogOnComplete(apiPackageSettings.getCeremony().getEvents().getComplete().getDialog());

        if (apiPackageSettings.getCeremony().getHandOver() != null) {
            result.setLinkText(apiPackageSettings.getCeremony().getHandOver().getText());
            result.setLinkTooltip(apiPackageSettings.getCeremony().getHandOver().getTitle());
            result.setLinkHref(apiPackageSettings.getCeremony().getHandOver().getHref());
        }

        result.setCeremonyLayoutSettings( new CeremonyLayoutSettingsBuilder(apiPackageSettings.getCeremony()).build());

        return result;
    }
    
}
