package com.stackroute.serviceTest;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.SameTrackCommentsException;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TrackServiceTest {
        private Optional optional;
        @Mock
        TrackRepository trackRepository;

        @InjectMocks
        TrackServiceImplementation trackService;

        Track track;
        List<Track> track1;

        @Before
        public void setUp(){
            MockitoAnnotations.initMocks(this);
            track1 = new ArrayList<>();
            track = new Track("track1","comment1");
            track1.add(track);
            track1.add(new Track("track2","comment2"));
            optional = Optional.of(track);
        }

        @Test
        public void testSaveTrack() throws TrackAlreadyExistsException {
            when(trackRepository.save(track)).thenReturn(track);
            Track actual = trackService.saveTrack(track);
            assertEquals(track,actual);
            verify(trackRepository,times(1)).save(track);
        }

        @Test
        public void testGetAllTracks() throws Exception{
            when(trackRepository.findAll()).thenReturn(track1);
            List<Track> actual = trackService.getAllTracks();
            assertEquals(track1,actual);
            verify(trackRepository,times(1)).findAll();
        }

        @Test
        public void deleteTrack() throws TrackNotFoundException {
            when(trackRepository.findById(1)).thenReturn(optional);
            Track actual = trackService.deleteTrackById(1);
            assertEquals(optional.get(),actual);
            verify(trackRepository,times(1)).deleteById(1);
        }

        @Test
        public void updateTrack() throws TrackNotFoundException, SameTrackCommentsException, TrackAlreadyExistsException{
            when(trackRepository.findById(track.getTrackId())).thenReturn(optional);
            when(trackRepository.existsById(track.getTrackId())).thenReturn(true);
            when(trackRepository.save(track)).thenReturn(track);
            Track actual = trackService.updateTrack(track.getTrackId(),"new comment");
            Track expected = new Track(track.getTrackId(),"track1","new comment");
            System.out.println(expected.getTrackName()+" , "+actual.getTrackName());
            assertEquals(expected.getTrackId(),actual.getTrackId());
            assertEquals(expected.getTrackName(),actual.getTrackName());
            assertEquals(expected.getTrackComments(),actual.getTrackComments());
            verify(trackRepository,times(1)).findById(track.getTrackId());
            verify(trackRepository,times(1)).existsById(track.getTrackId());
            verify(trackRepository,times(1)).save(track);
        }
}

