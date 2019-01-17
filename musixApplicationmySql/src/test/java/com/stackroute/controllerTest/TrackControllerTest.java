package com.stackroute.controllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.serviceTest.TrackService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrackController.class)
public class TrackControllerTest {

    private Track track;

    @MockBean //Mocking dependency injection and makes independent classes
    private TrackService trackService;

    @InjectMocks //To create object of test class type
    private TrackController trackController;

    @Autowired //To build the class.
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();
        track = new Track("track355","comment3444");
    }

    @Test
    public void saveTrack() throws TrackAlreadyExistsException, Exception{
        String uri = "/api/v1/track";
        this.mockMvc
                .perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonToString(track)))
                .andExpect(status().isCreated());//Compares the status code
    }
    //This method converts the json format to string
    private static String jsonToString(final Object obj) throws JsonProcessingException {
        String result;
        try {

            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "Json processing error";
        }
        return result;
    }
    @Test
    public void getAllTracks() throws Exception{
        String uri = "/api/v1/tracks";
        mockMvc
                .perform(get(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonToString(track)))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteTrack() throws Exception{
//        when(trackService.saveTrack(track)).thenReturn(track);
        String uri = "/api/v1/track/1"; //Dont give {id}, it gives illegal argument exception. Instead, give values.
        mockMvc
                .perform(delete(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonToString(track)))
                .andExpect(status().isOk());
    }
    @Test
    public void updateTrack() throws Exception{
//        when(trackService.saveTrack(track)).thenReturn(track);
        String uri = "/api/v1/track/1/new comment";
        mockMvc
                .perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonToString(track)))
                .andExpect(status().isOk());
    }
}
