package com.team5.techradar.map;

import com.team5.techradar.model.Vote;
import com.team5.techradar.model.dto.VoteResponse;
import org.modelmapper.PropertyMap;

public class VoteResponseMap extends PropertyMap<Vote, VoteResponse> {
    @Override
    protected void configure() {
        map(source.getId(), destination.getId());
        using(TechnologyResponseMap.leveConverter).map(source.getLevel(), destination.getLevel());
        map(source.getUser(), destination.getUser());
        map(source.getTechnology(), destination.getTechnology());
    }
}
