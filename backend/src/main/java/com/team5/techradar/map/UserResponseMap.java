package com.team5.techradar.map;

import com.team5.techradar.model.Role;
import com.team5.techradar.model.User;
import com.team5.techradar.model.dto.UserResponse;
import org.modelmapper.PropertyMap;

public class UserResponseMap extends PropertyMap<User, UserResponse> {
    @Override
    protected void configure() {
        map(source.getId(), destination.getId());
        map(source.getEmail(), destination.getEmail());
        map(source.getSpecialization().getName(), destination.getSpecialization());
        using(context -> context.getSource() == Role.ROLE_ADMIN)
                .map(source.getRole(), destination.isAdmin());
    }
}
