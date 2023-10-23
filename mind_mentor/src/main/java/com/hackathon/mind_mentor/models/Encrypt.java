package com.hackathon.mind_mentor.models;

import com.hackathon.mind_mentor.components.EncryptionUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.attribute.Attribute;

@Converter
public class Encrypt implements AttributeConverter<String,String> {

    @Autowired
    EncryptionUtil encryptionUtil;

    @Override
    public String convertToDatabaseColumn(String message){
        return encryptionUtil.encrypt(message);
    }

    @Override
    public String convertToEntityAttribute(String message){
        return encryptionUtil.decrypt(message);
    }
}
