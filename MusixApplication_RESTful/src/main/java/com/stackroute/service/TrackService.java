package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TrackService {
    public Track saveTrack(Track track);
    public List<Track> getAllTracks();
    public Optional deleteByIdInTracks(int id) throws TrackNotFoundException;
    public Track updateTrackListById(int id, String comments, Track track) throws TrackNotFoundException;
}
