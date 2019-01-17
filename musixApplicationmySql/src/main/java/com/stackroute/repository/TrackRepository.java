package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TrackRepository extends CrudRepository<Track, Integer> {
@Query(value = "select * from track t where trackName = t.track1")
   public List<Track> findByTrackName(String trackName);

}
