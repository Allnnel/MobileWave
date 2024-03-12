import com.example.AccessControlServiceApplication;
import com.example.controller.BlockController;
import com.example.exception.CustomException;
import com.example.response.ResponseMessage;
import com.example.response.UsersBlockResponseMessage;
import com.example.service.BlockListService;
import com.example.service.UsersBlockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = AccessControlServiceApplication.class)
public class BlockControllerTest {

    @Mock
    private UsersBlockService usersBlockService;

    private BlockController blockController;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testCheckBlockIsException() {
        String ctn = "testCtn";
        String token = "testToken";
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.getForEntity(anyString(), eq(Void.class))).thenThrow(HttpServerErrorException.class);
        blockController = new BlockController(usersBlockService, restTemplate);
        ResponseEntity<UsersBlockResponseMessage> messageResponseEntity = null;
        try {
            blockController.checkBlock(ctn, token);
            fail("должно быть исключение");
        } catch (CustomException e) {
        }
    }

    @Test
    public void testCheckBlockIsEmpty() {
        String ctn = "testCtn";
        String token = "testToken";
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.getForEntity(anyString(), eq(Void.class))).thenReturn(responseEntity);
        blockController = new BlockController(usersBlockService, restTemplate);
        try {
            ResponseEntity<UsersBlockResponseMessage> messageResponseEntity = blockController.checkBlock(ctn, token);
            assertEquals("Successes", messageResponseEntity.getBody().getStatus());
            assertEquals(null, messageResponseEntity.getBody().getMessage());
            assertEquals("200", messageResponseEntity.getBody().getCode());
            assertTrue(messageResponseEntity.getBody().getBlockList().isEmpty());
        } catch (CustomException e) {
            fail("не должно быть исключения");
        }
    }

    @Test
    public void testPostCheckBlock() {
        String ctn = "testCtn";
        String token = "testToken";
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.getForEntity(anyString(), eq(Void.class))).thenReturn(responseEntity);
        blockController = new BlockController(usersBlockService, restTemplate);
        try {
            ResponseEntity<ResponseMessage> messageResponseEntity =
                    blockController.postCheckBlock("123", "myToken", "blockType", new Date(), "description");
            assertEquals("Successes", messageResponseEntity.getBody().getStatus());
            assertEquals(null, messageResponseEntity.getBody().getMessage());
            assertEquals("200", messageResponseEntity.getBody().getCode());
        } catch (CustomException e) {
            fail("не должно быть исключения");
        }
    }

    @Test
    public void testPostCheckBlockIsExceptionOne() {
        String ctn = "testCtn";
        String token = "testToken";
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.getForEntity(anyString(), eq(Void.class))).thenThrow(HttpServerErrorException.class);
        blockController = new BlockController(usersBlockService, restTemplate);
        ResponseEntity<UsersBlockResponseMessage> messageResponseEntity = null;
        try {
            blockController.postCheckBlock("123", "myToken", "blockType", new Date(), "description");
            fail("должно быть исключение");
        } catch (CustomException e) {
        }
    }

    @Test
    public void testPutCheckBlock() {
        String ctn = "testCtn";
        String token = "testToken";
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.getForEntity(anyString(), eq(Void.class))).thenReturn(responseEntity);
        blockController = new BlockController(usersBlockService, restTemplate);
        try {
            ResponseEntity<ResponseMessage> messageResponseEntity = blockController.putCheckBlock("123", "myToken", "blockType", new Date(), "description");
            assertEquals("Successes", messageResponseEntity.getBody().getStatus());
            assertEquals(null, messageResponseEntity.getBody().getMessage());
            assertEquals("200", messageResponseEntity.getBody().getCode());
        } catch (CustomException e) {
            fail("не должно быть исключения");
        }
    }

    @Test
    public void testDeleteCheckBlock() {
        String ctn = "testCtn";
        String token = "testToken";
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.getForEntity(anyString(), eq(Void.class))).thenReturn(responseEntity);
        blockController = new BlockController(usersBlockService, restTemplate);
        try {
            ResponseEntity<ResponseMessage> messageResponseEntity = blockController.deleteCheckBlock("123", "myToken", "blockType");
            assertEquals("Successes", messageResponseEntity.getBody().getStatus());
            assertEquals(null, messageResponseEntity.getBody().getMessage());
            assertEquals("200", messageResponseEntity.getBody().getCode());
        } catch (CustomException e) {
            fail("не должно быть исключения");
        }
    }

    @Test
    public void testDeleteCheckBlockIsExceptionOne() {
        String ctn = "testCtn";
        String token = "testToken";
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.getForEntity(anyString(), eq(Void.class))).thenThrow(HttpServerErrorException.class);
        blockController = new BlockController(usersBlockService, restTemplate);
        ResponseEntity<UsersBlockResponseMessage> messageResponseEntity = null;
        try {
            blockController.deleteCheckBlock("123", "myToken", "blockType");
            fail("должно быть исключение");
        } catch (CustomException e) {
        }
    }

}
