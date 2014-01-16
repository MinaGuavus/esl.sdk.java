package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.api.model.FieldValidation;
import com.silanis.esl.sdk.FieldValidator;

import java.util.ArrayList;

/**
 * Converter between SDK FieldValidator and API FieldValidation.
 */
public class FieldValidatorConverter {
    private Optional<FieldValidator> fieldValidatorOptional;
    private Optional<FieldValidation> fieldValidationOptional;

    /**
     * Construct with SDK object involved in conversion.
     *
     * @param fieldValidator
     */
    public FieldValidatorConverter( FieldValidator fieldValidator ) {
        this.fieldValidatorOptional = Optional.of( fieldValidator );
        this.fieldValidationOptional = Optional.absent();
    }

    /**
     * Construct with API object involved in conversion.
     *
     * @param fieldValidation
     */
    public FieldValidatorConverter(FieldValidation fieldValidation) {
        this.fieldValidatorOptional = Optional.absent();
        this.fieldValidationOptional = Optional.of( fieldValidation );
    }

    /**
     * Convert from SDK to API.
     *
     * @return a FieldValidation object.
     */
    public FieldValidation toAPIFieldValidation() {
        if (fieldValidationOptional.isPresent()) {
            return fieldValidationOptional.get();
        }
        FieldValidation fieldValidation = new FieldValidation();

        if ( fieldValidatorOptional.get().getMaxLength() != null ) {
            fieldValidation.setMaxLength( fieldValidatorOptional.get().getMaxLength() );
        }

        if ( fieldValidatorOptional.get().getMinLength() != null ) {
            fieldValidation.setMinLength( fieldValidatorOptional.get().getMinLength() );
        }

        if ( !fieldValidatorOptional.get().getOptions().isEmpty() ) {
            fieldValidation.setEnum( new ArrayList<String>() );
            fieldValidation.getEnum().addAll( fieldValidatorOptional.get().getOptions() );
        }

        fieldValidation.setRequired( fieldValidatorOptional.get().isRequired() );
        if ( fieldValidatorOptional.get().getErrorMessage() != null ) {
            fieldValidation.setErrorMessage( fieldValidatorOptional.get().getErrorMessage() );
        }

        if ( fieldValidatorOptional.get().getRegex() != null ) {
            fieldValidation.setPattern( fieldValidatorOptional.get().getRegex() );
        }

        return fieldValidation;
    }

    /**
     * Convert from API to SDK.
     *
     * @return a FieldValidator object.
     */
    public FieldValidator toSDKFieldValidator() {
        if (fieldValidatorOptional.isPresent()) {
            return fieldValidatorOptional.get();
        }
        FieldValidator fieldValidator = new FieldValidator();
        fieldValidator.setErrorMessage(fieldValidationOptional.get().getErrorMessage());

        if ( fieldValidationOptional.get().getMaxLength() != null )
            fieldValidator.setMaxLength(fieldValidationOptional.get().getMaxLength());

        if ( fieldValidationOptional.get().getMinLength() != null )
            fieldValidator.setMinLength(fieldValidationOptional.get().getMinLength());

        fieldValidator.setRegex(fieldValidationOptional.get().getPattern());
        fieldValidator.setRequired(fieldValidationOptional.get().evalRequired());

        return fieldValidator;
    }
}
