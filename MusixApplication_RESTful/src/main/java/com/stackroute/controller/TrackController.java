package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class TrackController {
    @Autowired
    private TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    @RequestMapping(value = "track", method = RequestMethod.POST)
    public ResponseEntity<?> addTrack(@RequestBody Track track){
        try {
            Track track1 = trackService.saveTrack(track);
            return new ResponseEntity<Track>(track1, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "tracks", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTracks(){
        try {
            List<Track> tracks = trackService.getAllTracks();
            return new ResponseEntity<List<Track>>(tracks, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "track/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeTrack(@PathVariable(value = "id") int id){

        try {
            Track track = (Track) trackService.deleteByIdInTracks(id).get();
            return new ResponseEntity<Track>(track,HttpStatus.OK);
        }
        catch (TrackNotFoundException trackNotFoundException){
            return new ResponseEntity<>(trackNotFoundException.getMessage(),HttpStatus.CONFLICT);

        }
        catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "track/{id}/{comments}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTrack(@PathVariable(value = "id") int id, @PathVariable(value = "comments") String comments, @RequestBody Track track){
        try{
            Track track1 = (Track) trackService.updateTrackListById(id, comments, track);
            return new ResponseEntity<Track>(track1, HttpStatus.OK);
        }catch (TrackNotFoundException trackNotFoundException){
            return new ResponseEntity<>(trackNotFoundException.getMessage(), HttpStatus.CONFLICT);
        }
        catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
