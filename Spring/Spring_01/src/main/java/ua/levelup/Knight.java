package ua.levelup;

import java.util.List;

public interface Knight {
    void setKnightName(String knightName);
    void embarkOnQuest();
    void appearingOnTheScene();
    void lastScene();
    void setSword(Sword sword);
    List<Damsel> getDamselList();
}
