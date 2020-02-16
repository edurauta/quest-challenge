package ie.quest.challenge;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@Transactional
public class ApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

    @Before
    public void setup(){
	    // Process mock annotations
	    MockitoAnnotations.initMocks(this);
	
	    // Setup Spring test in webapp-mode (same config as spring-boot)
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testRootPage() throws Exception{
        this.mockMvc.perform(get("/")).andExpect(status().isOk());  
    }
    
    @Test
    public void testListPage() throws Exception{
        this.mockMvc.perform(get("/list")).andExpect(status().isOk())
        	.andExpect(content().string(Matchers.containsString("No records have been created")));  
    }
    
    @Test
    public void testRegisterPage() throws Exception{
        this.mockMvc.perform(get("/register")).andExpect(status().isOk());  
    }
	
    @Test
    public void testPost() {
        try {
            this.mockMvc.perform(post("/register")
            						.param("name", "Test")
            						.param("pps", "123")
            						.param("birth", "2000-06-01")
            						.param("mobile", "")
            						).andExpect(status().is(302))
                    .andExpect(view().name("redirect:/register?success"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testListWithPost() throws Exception{
    	this.mockMvc.perform(post("/register")
				.param("name", "Test Post")
				.param("pps", "123")
				.param("birth", "2000-06-01")
				.param("mobile", "")
				);
    	
        this.mockMvc.perform(get("/list")).andExpect(status().isOk())
        	.andExpect(content().string(Matchers.containsString("Test Post")));  
    }
    
    @Test
    public void testUnderAgePost() {
        try {
            this.mockMvc.perform(post("/register")
            						.param("name", "Test")
            						.param("pps", "123")
            						.param("birth", "2010-06-01")
            						.param("mobile", "")
            						)
            		.andExpect(status().isOk())
                    .andExpect(view().name("register"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testDuplicatePpsPost() {
        try {
        	this.mockMvc.perform(post("/register")
					.param("name", "Test")
					.param("pps", "123")
					.param("birth", "2000-06-01")
					.param("mobile", "")
					);
            this.mockMvc.perform(post("/register")
            						.param("name", "Test")
            						.param("pps", "123")
            						.param("birth", "2000-06-01")
            						.param("mobile", "")
            						).andExpect(status().isOk())
                    .andExpect(view().name("register"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testMobilePost() {
        try {
            this.mockMvc.perform(post("/register")
            						.param("name", "Test")
            						.param("pps", "123")
            						.param("birth", "2010-06-01")
            						.param("mobile", "0123456")
            						).andExpect(status().isOk())
                    .andExpect(view().name("register"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
