package com.stackroute.controllerTest;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.SameTrackCommentsException;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.serviceTest.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class TrackController {

    private TrackService trackService;

    private ResponseEntity <?>responseEntity= null;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

//    @RequestMapping(value = "track", method = RequestMethod.POST)
    @PostMapping(value = "track")
    public ResponseEntity<?> addTrack(@RequestBody Track track){

        try {
            Track track1 = trackService.saveTrack(track);
            responseEntity= new ResponseEntity<Track>(track1, HttpStatus.CREATED);
        }catch (TrackAlreadyExistsException trackAlreadyExistsException){
            responseEntity =  new ResponseEntity<>(trackAlreadyExistsException.getMessage(), HttpStatus.CONFLICT);
        }catch(Exception exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return responseEntity;
    }
//    @RequestMapping(value = "tracks", method = RequestMethod.GET)
    @GetMapping(value = "tracks")
    public ResponseEntity<?> getAllTracks(){
        try {
            List<Track> tracks = trackService.getAllTracks();
            responseEntity = new ResponseEntity<List<Track>>(tracks, HttpStatus.OK);
        }catch (Exception exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
//    @RequestMapping(value = "track/{id}", method = RequestMethod.DELETE)
    @DeleteMapping(value = "track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable(value = "id") int id){

        try {
            Track track = (Track) trackService.deleteTrackById(id);
            responseEntity = new ResponseEntity<Track>(track,HttpStatus.OK);
        }
        catch (TrackNotFoundException trackNotFoundException){
            responseEntity = new ResponseEntity<>(trackNotFoundException.getMessage(),HttpStatus.CONFLICT);

        }
        catch (Exception exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
//    @RequestMapping(value = "track/{id}/{comments}", method = RequestMethod.PUT)
    @PutMapping(value = "track/{id}/{comment}")
    public ResponseEntity<?> updateTrack(@PathVariable(value = "id") int id, @PathVariable(value = "comment") String comment){
        try{
            Track track1 = (Track) trackService.updateTrack(id,comment);
            responseEntity = new ResponseEntity<Track>(track1, HttpStatus.OK);
        }catch (SameTrackCommentsException sameTrackComments){
            responseEntity = new ResponseEntity<>(sameTrackComments.getMessage(), HttpStatus.CONFLICT);
        }catch (TrackNotFoundException trackNotFound){
            responseEntity = new ResponseEntity<>(trackNotFound.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
//    @GetMapping(value = "track/{trackname}")
//    public ResponseEntity<?> findBytrackName(@PathVariable(value = "trackname") String trackName){
//        Track track1 =(Track) trackService.findByTrackName(trackName);
//        responseEntity = new ResponseEntity<Track>(track1, HttpStatus.OK);
//        return responseEntity;
//    }
}
