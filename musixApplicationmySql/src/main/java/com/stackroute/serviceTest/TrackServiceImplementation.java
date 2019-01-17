package com.stackroute.serviceTest;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.SameTrackCommentsException;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImplementation implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImplementation(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        Optional<Track> fetchedtrack = trackRepository.findById(track.getTrackId());
        if(fetchedtrack.isPresent()){
            throw new TrackAlreadyExistsException("Track already exists");
        }
        else
        {
            return trackRepository.save(track);
        }
    }

    @Override
    public List<Track> getAllTracks() throws Exception {
        List<Track> trackList = (List<Track>) trackRepository.findAll();
        return trackList;
    }

    @Override
    public Track deleteTrackById(int id) throws TrackNotFoundException{
        Optional<Track> track = trackRepository.findById(id);
        if(track.isPresent()) {
            trackRepository.deleteById(id);
        }
        else {
            throw new TrackNotFoundException("Track not found");
        }
        return track.get();
    }
    @Override
    public Track updateTrack(int id, String comment) throws TrackNotFoundException, SameTrackCommentsException {
        Track track = trackRepository.findById(id).get();
        Track track1;
        if (!trackRepository.existsById(id)) {
            System.out.println("before save");
            throw new TrackNotFoundException("Track not found");
        } else if (comment.equals(track.getTrackComments())) {
            throw new SameTrackCommentsException("Same track comments");
        } else {
            track.setTrackComments(comment);
            track1 = trackRepository.save(track);
        }
        return track1;
    }
//    @Override
//    public List<Track> findByTrackName(String trackname){
//        return trackRepository.findByTrackName(trackname);
//    }
}
