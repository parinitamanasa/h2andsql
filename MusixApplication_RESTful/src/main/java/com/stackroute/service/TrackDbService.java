package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackDbService implements TrackService {

    @Autowired
    TrackRepository trackRepository;

    public TrackDbService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public void setTrackRepository(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track trackInformation) {
        Track track = trackRepository.save(trackInformation);
        return track;
    }

    @Override
    public List<Track> getAllTracks() {
        List<Track> trackList = (List<Track>) trackRepository.findAll();
        return trackList;
    }

    @Override
    public Optional deleteByIdInTracks(int id) throws TrackNotFoundException{
        if(trackRepository.existsById(id))
               trackRepository.deleteById(id);
        else {
            throw new TrackNotFoundException("Track not found");
        }
        Optional<Track> track = trackRepository.findById(id);
        return track;
    }
    @Override
    public Track updateTrackListById(int id, String comments, Track track) throws TrackNotFoundException {
        Track track1 = null;
        if (trackRepository.existsById(id)) {
                track.setTrackId(id);
                track.setTrackComments(comments);
                track1 = trackRepository.save(track);
        } else {
            throw new TrackNotFoundException("Track not found");
        }
        return track1;
    }
}
