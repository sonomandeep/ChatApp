import it.mandeep.chatapp.server.core.Server;
import it.mandeep.chatapp.server.database.dao.UtenteDao;

public class MainTest {

    public static void main(String[] args) {
        UtenteDao utenteDao = new UtenteDao();
        new Server();
    }

}
