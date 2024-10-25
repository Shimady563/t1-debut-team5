package com.team5.techradar.map;

import com.team5.techradar.model.Vote;
import com.team5.techradar.model.dto.VoteCreationRequest;
import org.modelmapper.PropertyMap;

public class VoteCreationRequestMap extends PropertyMap<VoteCreationRequest, Vote> {
    @Override
    protected void configure() {
        map(source.getLevel(), destination.getLevel());
        skip(source.getTechnologyId(), destination.getTechnology().getId());
    }
}
