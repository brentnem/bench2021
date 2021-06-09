package bench2021;

import org.junit.Test;

import bench2021.exceptions.ClientException;

import java.io.IOException;

public class AppTest {

    @Test
    public void testRunningBalance() throws IOException, InterruptedException, ClientException {
        App app = new App();
        app.calculateBalances();
    }
}
