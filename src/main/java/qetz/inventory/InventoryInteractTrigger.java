package qetz.inventory;

import com.google.inject.Inject;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import qetz.components.Component;
import qetz.inventory.actions.DefaultActions;
import qetz.inventory.open.OpenInventoryRepository;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = @Inject)
@Component
public final class  InventoryInteractTrigger implements Listener {
  private final OpenInventoryRepository openInventories;
  private final DefaultActions actions;

  @EventHandler
  private void callInventoryInteract(InventoryClickEvent click) {
    var userId = click.getWhoClicked().getUniqueId();

    if (!isEventValid(click)) {
      return;
    }

    openInventories.performActionForUser(actions.triggerInteract(click), userId);
  }

  private boolean isEventValid(InventoryClickEvent click) {
    return click.getInventory() != null
      && click.getCurrentItem() != null
      && click.getCurrentItem().getType() != null
      && click.getCurrentItem().getType() != Material.AIR
      && click.getCursor() != null
      && click.getWhoClicked() instanceof Player;
  }
}