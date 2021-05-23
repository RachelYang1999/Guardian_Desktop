package facade;

import model.domain.Entity;

public interface EngineFacade {
    Entity login(String token);

    void userLogOut();

}

