package com.stackroute.serviceTest;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.SameTrackCommentsException;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;
    public List<Track> getAllTracks() throws Exception;
    public Track deleteTrackById(int id) throws TrackNotFoundException;
    public Track updateTrack(int id, String comment) throws TrackNotFoundException, SameTrackCommentsException;
   public List<Track> findByTrackName(String trackName);
}
