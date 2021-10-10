package dev._2lstudios.squidgame.commands.admin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev._2lstudios.jelly.annotations.Command;
import dev._2lstudios.jelly.commands.CommandContext;
import dev._2lstudios.jelly.commands.CommandExecutionTarget;
import dev._2lstudios.jelly.commands.CommandListener;
import dev._2lstudios.squidgame.errors.ArenaMisconfiguredException;
import dev._2lstudios.squidgame.errors.NoAvailableArenaException;
import dev._2lstudios.squidgame.player.PlayerWand;
import dev._2lstudios.squidgame.player.SquidPlayer;

@Command(name = "wand", usage = "/squid wand", description = "Give you region wand", permission = "squidgame.admin.wand", target = CommandExecutionTarget.ONLY_PLAYER)
public class SquidWandCommand extends CommandListener {
    @Override
    public void handle(CommandContext context) throws NoAvailableArenaException, ArenaMisconfiguredException {
        final ItemStack item = new ItemStack(Material.BLAZE_ROD);
        final ItemMeta meta = item.getItemMeta();
        final List<String> lore = new ArrayList<>();

        lore.add("§7");
        lore.add("§aLeft-click: §eSet first point.");
        lore.add("§aRight-click: §eSet second point.");
        lore.add("§7");
        meta.setLore(lore);

        meta.setDisplayName("§dRegion wand §7(Left/Right click)");
        item.setItemMeta(meta);

        context.getPlayer().getInventory().clear();
        context.getPlayer().getInventory().addItem(item);
        context.getPlayer().updateInventory();

        SquidPlayer squidPlayer = (SquidPlayer) context.getPluginPlayer();
        squidPlayer.createWand(new PlayerWand());
    }
}
