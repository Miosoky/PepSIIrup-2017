package event;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.eventService.config.WebConfig;

import com.eventService.event.EventController;
import com.eventService.event.EventService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {WebConfig.class})
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class EventControllerTest {
	
    @Configuration
    static class ContextConfiguration {

        // this bean will be injected into the OrderServiceTest class
        @Bean
        public EventController orderService() {
        	EventController eventController = new EventController();
            // set properties, etc.
            return eventController;
        }
    }

    @Autowired
    private EventController eventController;
    
	private MockMvc mockMvc;
	
	@Mock
	private EventService eventService;
	
	 /*@InjectMocks
	 private EventController eventController;*/
	
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(eventController)
                .build();
    }
	
	@Test
	public void coucou() throws Exception{
		mockMvc.perform(get("/coucou")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.texte").value("coucou depuis l'autre côté!"));
	}
}
