package dev._2lstudios.squidgame;

import dev._2lstudios.jelly.JellyPlugin;
import dev._2lstudios.squidgame.commands.SquidGameCommand;

public class SquidGame extends JellyPlugin {

    @Override
    public void onEnable() {
        this.addCommand(new SquidGameCommand());
    }

}