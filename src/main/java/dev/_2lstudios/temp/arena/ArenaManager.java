package dev._2lstudios.temp.arena;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import dev._2lstudios.jelly.utils.FileUtils;

public class ArenaManager {

    private final List<Arena> arenas;
    private final File path;

    public ArenaManager(final File path) {
        this.arenas = new ArrayList<>();
        this.path = path;
    }

    public void clear() {
        this.arenas.clear();
    }

    public List<Arena> getArenas() {
        return this.arenas;
    }

    public void load() {
        for (File file : this.path.listFiles()) {
            if (file.getName().endsWith(".json")) {
                final String id = file.getName().split(".")[0];
                final String content = FileUtils.readFile(file);

                final Gson gson = new Gson();
                final ArenaSettings settings = gson.fromJson(content, ArenaSettings.class);

                final Arena arena = new Arena(id, settings);
                this.arenas.add(arena);
            }
        }
    }
}
