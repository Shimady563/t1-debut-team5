package com.team5.techradar.map;

import com.team5.techradar.model.User;
import com.team5.techradar.model.dto.UserRegistrationRequest;
import org.modelmapper.PropertyMap;

public class UserRegistrationRequestMap extends PropertyMap<UserRegistrationRequest, User> {
    @Override
    protected void configure() {
        map(source.getEmail(), destination.getEmail());
        map(source.getPassword(), destination.getPassword());
        skip(source.getSpecializationId(), destination.getId());
    }
}
