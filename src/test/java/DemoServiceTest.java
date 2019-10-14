import com.konka.datademo.Application;
import com.konka.datademo.service.DataBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DemoServiceTest {

    @Autowired
    private DataBaseService dataBaseService;







}
