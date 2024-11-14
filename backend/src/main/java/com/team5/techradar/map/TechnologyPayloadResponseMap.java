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
        using(usageConverter).map(source.getUsageLevel(), destination.getLevel());
    }

    private final static Converter<String, Integer> categoryConverter = (context) -> switch (context.getSource()) {
        case "Platforms" -> 0;
        case "Languages" -> 1;
        case "Databases" -> 2;
        default -> 3;
    };

    private final static Converter<Double, String> usageConverter = (context) -> {
        Double source = context.getSource();
        if (source > 0.75) {
            return "Assess";
        } else if (source > 0.5) {
            return "Adopt";
        } else if (source > 0.25) {
            return "Trial";
        }
        return "Hold";
    };
}
