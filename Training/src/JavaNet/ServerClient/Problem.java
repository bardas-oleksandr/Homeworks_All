package JavaNet.ServerClient;

import Interfaces.IProblem;
import Interfaces.IService;

import java.io.IOException;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Server server = new Server();
        Client client = new Client();
        try {
            client.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
