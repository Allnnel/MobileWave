import com.example.AccessControlServiceApplication;
import com.example.exception.CustomException;
import com.example.model.BlockList;
import com.example.model.UsersBlock;
import com.example.repository.UsersBlockRepository;
import com.example.service.BlockListService;
import com.example.service.impl.UsersBlockServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = AccessControlServiceApplication.class)
public class UsersBlockServiceTest {

    @Mock
    private UsersBlockRepository usersBlockRepository;

    @InjectMocks
    private UsersBlockServiceImpl usersBlockService;

    @Mock
    private BlockListService blockListService;

    @Test
    public void testFindByCtnAndBlockType() {
        assertThrows(CustomException.class, () -> usersBlockService.findByCtnAndBlockType("blockType", "ctn"));
    }

    @Test
    public void testFindByCtnAndBlockType_WhenNotEmpty() throws CustomException {
        UsersBlock usersBlock = new UsersBlock("123", "Block", new Date(), new Date(), "description");

        when(usersBlockRepository.findByCtnAndBlockType(anyString(), anyString()))
                .thenReturn(Optional.of(usersBlock));

        Optional<UsersBlock> result = usersBlockService.findByCtnAndBlockType("blockType", "ctn");

        assertTrue(result.isPresent());
    }

    @Test
    public void testFindByCtn() {
        assertThrows(CustomException.class, () -> usersBlockService.findByCtn("+123"));
    }

    @Test
    public void testFindByCtn_WhenNotEmpty() throws CustomException {
        List <UsersBlock> usersBlock = new ArrayList<>();
        usersBlock.add(new UsersBlock());

        when(usersBlockRepository.findByCtn(anyString()))
                .thenReturn(usersBlock);

        List<UsersBlock> result = usersBlockService.findByCtn("123");

        assertFalse(result.isEmpty());
    }

    @Test
    public void testSave() throws CustomException {
        when(blockListService.findByBlockType(anyString()))
                .thenReturn(new BlockList("Block", ""));

        usersBlockService.save(new UsersBlock("ctn", "blockType", new Date(), new Date(), ""));
    }

    @Test
    public void testSave_WhenNotEmpty() throws CustomException {
        when(blockListService.findByBlockType(anyString()))
                .thenReturn(new BlockList("Block", ""));

        when(usersBlockRepository.findByCtnAndBlockType(anyString(), anyString()))
                .thenReturn(Optional.of(new UsersBlock("ctn", "blockType", new Date(), new Date(), "")));

        assertThrows(CustomException.class, () ->
                usersBlockService.save(new UsersBlock("ctn", "blockType", new Date(), new Date(), "")));
    }

    @Test
    public void testDelete() {
        assertThrows(CustomException.class, () ->
        usersBlockService.deleteByCtnAndBlockType("ctn", "blockType"));
    }
}
