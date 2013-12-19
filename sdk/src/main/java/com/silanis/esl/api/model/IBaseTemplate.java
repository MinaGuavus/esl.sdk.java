package com.silanis.esl.api.model;
//
import java.util.List;
import java.util.Map;

public interface IBaseTemplate extends IBasePackage{
    public IBaseTemplate setAutocomplete( Boolean value);
    public Boolean getAutocomplete();
    public IBaseTemplate setConsent( String value);
    public String getConsent();
    public IBaseTemplate setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public IBaseTemplate setDescription( String value);
    public String getDescription();
    public IBaseTemplate setDocuments( List<Document> value);
    public List<Document> getDocuments();
    public IBaseTemplate setDue( java.util.Date value);
    public java.util.Date getDue();
    public IBaseTemplate setEmailMessage( String value);
    public String getEmailMessage();
    public IBaseTemplate setId( String value);
    public String getId();
    public IBaseTemplate setLanguage( String value);
    public String getLanguage();
    public IBaseTemplate setLimits( PackageArtifactsLimits value);
    public PackageArtifactsLimits getLimits();
    public IBaseTemplate setMessages( List<Message> value);
    public List<Message> getMessages();
    public IBaseTemplate setName( String value);
    public String getName();
    public IBaseTemplate setRoles( List<Role> value);
    public List<Role> getRoles();
    public IBaseTemplate setSender( Sender value);
    public Sender getSender();
    public IBaseTemplate setSettings( PackageSettings value);
    public PackageSettings getSettings();
    public IBaseTemplate setSignedDocumentDelivery( SignedDocumentDelivery value);
    public SignedDocumentDelivery getSignedDocumentDelivery();
    public IBaseTemplate setStatus( PackageStatus value);
    public PackageStatus getStatus();
    public IBaseTemplate setType( BasePackageType value);
    public BasePackageType getType();
    public IBaseTemplate setUpdated( java.util.Date value);
    public java.util.Date getUpdated();
    public IBaseTemplate setVisibility( Visibility value);
    public Visibility getVisibility();
    }