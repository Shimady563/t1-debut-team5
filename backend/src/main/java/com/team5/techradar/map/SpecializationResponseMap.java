package com.team5.techradar.map;

import com.team5.techradar.model.Specialization;
import com.team5.techradar.model.Vote;
import com.team5.techradar.model.dto.SpecializationResponse;
import com.team5.techradar.model.dto.VoteResponse;
import org.modelmapper.PropertyMap;

public class SpecializationResponseMap extends PropertyMap<Specialization, SpecializationResponse> {
    @Override
    protected void configure() {
        map(source.getId(), destination.getId());
        map(source.getName(), destination.getLabel());
    }
}
