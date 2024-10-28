package com.team5.techradar.map;

import com.team5.techradar.model.Technology;
import com.team5.techradar.model.User;
import com.team5.techradar.model.dto.TechnologyStatsResponse;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

import java.util.Collection;

import static com.team5.techradar.map.TechnologyResponseMap.*;

public class TechnologyStatsResponseMap extends PropertyMap<Technology, TechnologyStatsResponse> {
    @Override
    protected void configure() {
        map(source.getId(), destination.getId());
        map(source.getName(), destination.getName());
        using(movedConverter).map(source.getMoved(), destination.getMoved());
        using(leveConverter).map(source.getLevel(), destination.getLevel());
        using(typeConverter).map(source.getType(), destination.getType());
        map(source.getIsActive(), destination.getIsActive());
        using(usersConverter).map(source.getUsers(), destination.getScore());
    }

    private static final Converter<Collection<User>, Integer> usersConverter = context -> context.getSource().size();
}
