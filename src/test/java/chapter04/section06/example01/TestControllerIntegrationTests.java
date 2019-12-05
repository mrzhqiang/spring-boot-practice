package chapter04.section06.example01;

import chapter04.MyMvcConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyMvcConfig.class})
@WebAppConfiguration("src/main/resources")
public class TestControllerIntegrationTests {
  private MockMvc mockMvc;
  @Autowired
  private DemoService demoService;
  @Autowired
  private WebApplicationContext context;
  @Autowired
  private MockHttpSession session;
  @Autowired
  private MockHttpServletRequest request;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
  }

  @Test
  public void testNormalController() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/normal"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("page"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/classes/views/page.jsp"))
        .andExpect(MockMvcResultMatchers.model().attribute("msg", demoService.saySomething()));
  }

  @Test
  public void testRestController() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/testRest"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
        .andExpect(MockMvcResultMatchers.content().string(demoService.saySomething()));
  }
}
