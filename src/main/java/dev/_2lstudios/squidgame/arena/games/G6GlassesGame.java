package dev._2lstudios.squidgame.arena.games;

import org.bukkit.Material;
import org.bukkit.World;

import dev._2lstudios.jelly.math.Cuboid;
import dev._2lstudios.jelly.math.Vector3;
import dev._2lstudios.squidgame.arena.Arena;

public class G6GlassesGame extends ArenaGameBase {

    private Cuboid glassZone;

    public G6GlassesGame(final Arena arena, final int durationTime) {
        super("§bGlasses", "sixth", durationTime, arena);
    }

    public Cuboid getGlassZone() {
        if (this.glassZone == null) {
            this.glassZone = this.getArena().getConfig().getCuboid("games.sixth.glass_zone");
        }

        return this.glassZone;
    }

    @Override
    public void onExplainStart() {
        final World world = this.getArena().getWorld();

        final Vector3 first = this.getGlassZone().getFirstPoint();
        final Vector3 second = this.getGlassZone().getSecondPoint();

        world.getBlockAt(first.toLocation(world)).setType(Material.GLASS);
        world.getBlockAt(second.toLocation(world)).setType(Material.GLASS);

        // Obtener diferencia entre puntos X
        final int differenceBetweenX = (int) Math.abs(first.getX() - second.getX());
        // Obtener diferencia entre puntos Z
        final int differenceBetweenZ = (int) Math.abs(first.getZ() - second.getZ());
        // Verificar si se debe usar el Z como un indice
        final boolean useZAsIndex = differenceBetweenZ > differenceBetweenX;
        // Verificar si se debe aumentar o restar el valor indice
        final boolean shouldIncreaseIndex = useZAsIndex ? first.getZ() < second.getZ() : first.getX() < second.getX();

        // Obtener el width del suelo dependiendo hacia donde esté señalando el area
        final int groundWidth = (useZAsIndex ? differenceBetweenX : differenceBetweenZ) + 1;
        // Obtener el height del suelo dependiendo hacia donde esté señalando el area
        final int groundHeight = (useZAsIndex ? differenceBetweenZ : differenceBetweenX) + 1;

        // Parametros de las paltaformas
        final int size = groundWidth < 5 ? 1 : groundWidth < 8 ? 2 : 3;
        final int spaceXBetweenPlatforms = groundWidth - (size * 2);
        final int spaceZBetweenPlatforms = 3;

        // Indice de bloque (Incrementa o decrementa relativamente a la dirección de
        // generación)
        int blockIndex = (int) (useZAsIndex ? first.getZ() : first.getX());

        // Inicio del X, valor inmutable y absoluto.
        final int xStart = Math.min((int) first.getX(), (int) second.getX());
        // Inicio del Y, valor inmutable y absoluto
        final int yStart = (int) first.getY();
        // Inicio del Z, valor inmutable y absoluto.
        final int zStart = Math.max((int) first.getZ(), (int) second.getZ());

        // Obtener el número de pares de plataformas a generar dependiendo el tamaño
        final int platformGroups = groundHeight / (spaceZBetweenPlatforms + size);

        // Por cada grupo de plataforma (+ 1) iterar:
        for (int i = 0; i <= platformGroups; i++) {
            // En caso que la coordenada Z deba de usarse como un indice:
            if (useZAsIndex) {
                // Por cada posición relativa X de la plataforma
                for (int xPadding = 0; xPadding < size; xPadding++) {
                    // Por cada posición relativa Y de la plataforma
                    for (int zPadding = 0; zPadding < size; zPadding++) {
                        // Sumarle valor relativo x padding al valor absoluto x start
                        int x = xStart + xPadding;
                        // Sumarle el valor del indice Z al valor relativo z padding
                        int z = shouldIncreaseIndex ? blockIndex + zPadding : blockIndex - zPadding;

                        // Generar bloque en las coordenadas dadas X Y Z
                        world.getBlockAt(x, yStart, z).setType(Material.BEDROCK);
                        // Generar bloque en la misma posicion que el de arriba pero con una separación
                        world.getBlockAt(x + spaceXBetweenPlatforms + size, yStart, z).setType(Material.BEDROCK);
                    }
                }

                // En caso que la coordenada X deba de usarse como un indice:
            } else {
                // Por cada posición relativa X de la plataforma
                for (int xPadding = 0; xPadding < size; xPadding++) {
                    // Por cada posición relativa Y de la plataforma
                    for (int zPadding = 0; zPadding < size; zPadding++) {
                        // Sumarle el valor del indice X al valor relativo x padding
                        int x = shouldIncreaseIndex ? blockIndex + xPadding : blockIndex - xPadding;
                        // Sumarle valor relativo z padding al valor absoluto z start
                        int z = zStart + zPadding;

                        // Generar bloque en las coordenadas dadas X Y Z
                        world.getBlockAt(x, yStart, z).setType(Material.BEDROCK);
                        // Generar bloque en la misma posicion que el de arriba pero con una separación
                        world.getBlockAt(x, yStart, z + spaceXBetweenPlatforms + size).setType(Material.BEDROCK);
                    }
                }
            }

            final int separation = spaceZBetweenPlatforms + size;
            blockIndex = shouldIncreaseIndex ? blockIndex + separation : blockIndex - separation;
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onTimeUp() {
        this.getArena().killAllPlayers();
    }
}