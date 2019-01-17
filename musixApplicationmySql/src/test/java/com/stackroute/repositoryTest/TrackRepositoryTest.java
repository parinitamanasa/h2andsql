package com.stackroute.repositoryTest;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TrackRepositoryTest {
    @Autowired
    TrackRepository trackRepository;

    private Track track;
    List<Track> trackList;
    @Before
    public void setUp() throws Exception{
//        MockitoAnnotations.initMocks(this);
        track = new Track(3,"track21","comment21");
        trackList = new ArrayList<>();
        trackList.add(track);
        trackList.add(new Track(1,"track2","comment2"));
    }

    @Test
    public void testSave() throws Exception {
        trackRepository.save(track);
        Optional<Track> object = trackRepository.findById(track.getTrackId());
        assertThat(object.equals(track));
    }
    @Test
    public void testDelete() throws Exception {
        Track track1 = trackRepository.save(track);
        trackRepository.deleteById(track1.getTrackId());
        Optional<Track> track2 = trackRepository.findById(track1.getTrackId());
        assertTrue("No values present",!track2.isPresent());
    }
    @Test
    public void testFindAll(){
        trackRepository.save(track);
        trackRepository.save(new Track(1,"track2", "comment2"));
        List<Track> tracks = (List<Track>) trackRepository.findAll();
        assertThat(trackList.equals(tracks));
    }
}
