package com.team5.techradar.map;

import com.team5.techradar.model.Vote;
import com.team5.techradar.model.dto.UserVoteResponse;
import org.modelmapper.PropertyMap;

public class UserVoteResponseMap extends PropertyMap<Vote, UserVoteResponse> {
    @Override
    protected void configure() {
        map(source.getId(), destination.getId());
        using(TechnologyResponseMap.leveConverter).map(source.getLevel(), destination.getLevel());
        map(source.getTechnology(), destination.getTechnology());
    }
}
