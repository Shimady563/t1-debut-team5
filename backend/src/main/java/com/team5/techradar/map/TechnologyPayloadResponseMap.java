package com.team5.techradar.map;

import com.team5.techradar.model.dto.TechnologyPayload;
import com.team5.techradar.model.dto.TechnologyPayloadResponse;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

public class TechnologyPayloadResponseMap extends PropertyMap<TechnologyPayload, TechnologyPayloadResponse> {
    @Override
    protected void configure() {
        map(source.getId(), destination.getId());
        map(source.getName(), destination.getName());
        using(categoryConverter).map(source.getCategory(), destination.getType());
        using(usageConverter).map(source.getUsage(), destination.getLevel());
    }

    private final static Converter<String, Integer> categoryConverter = (context) -> {
        // will be replaced with values from other service
        return switch (context.getSource()) {
            case "Platform" -> 0;
            case "Laguages" -> 1;
            case "Databases" -> 2;
            default -> 3;
        };
    };

    private final static Converter<Long, String> usageConverter = (context) -> {
        // will be replaced with approximate usage form other service
        Long source = context.getSource();
        if (source > 1000) {
            return "Assess";
        } else if (source > 500) {
            return "Adopt";
        } else if (source > 100) {
            return "Trial";
        }
        return "Hold";
    };
}
