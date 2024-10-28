package com.team5.techradar.map;

import com.team5.techradar.model.Level;
import com.team5.techradar.model.Moved;
import com.team5.techradar.model.Technology;
import com.team5.techradar.model.Type;
import com.team5.techradar.model.dto.TechnologyResponse;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

public class TechnologyResponseMap extends PropertyMap<Technology, TechnologyResponse> {
    @Override
    protected void configure() {
        map(source.getId(), destination.getId());
        map(source.getName(), destination.getName());
        using(movedConverter).map(source.getMoved(), destination.getMoved());
        using(leveConverter).map(source.getLevel(), destination.getLevel());
        using(typeConverter).map(source.getType(), destination.getType());
        map(source.getIsActive(), destination.getIsActive());
    }

    protected static final Converter<Moved, Integer> movedConverter = context -> {
        if (context.getSource() == Moved.DOWN) {
            return -1;
        } else if (context.getSource() == Moved.NOT_MOVED) {
            return 0;
        }
        return 1;
    };

    protected static final Converter<Level, String> leveConverter = context -> context.getSource().getValue();

    protected static final Converter<Type, Integer> typeConverter = context -> context.getSource().ordinal();
}
