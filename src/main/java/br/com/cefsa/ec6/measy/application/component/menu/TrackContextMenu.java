package br.com.cefsa.ec6.measy.application.component.menu;

import br.com.cefsa.ec6.measy.domain.repository.spotify.PlaylistRepository;
import br.com.cefsa.ec6.measy.domain.repository.spotify.UserRepository;
import br.com.cefsa.ec6.measy.infrastructure.cache.Cache;
import br.com.cefsa.ec6.measy.infrastructure.util.AlertUtil;
import br.com.cefsa.ec6.measy.infrastructure.util.CacheHelper;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

@Component
public class TrackContextMenu {

  private static ContextMenu CONTEXT_MENU;
  private static String SELECTED_TRACK_URI;

  @Autowired private UserRepository userRepository;
  @Autowired private PlaylistRepository playlistRepository;
  @Autowired private Cache cache;
  @Autowired private CacheHelper cacheHelper;

  public TrackContextMenu() {
    CONTEXT_MENU = new ContextMenu();

    final Menu addToPlaylist = new Menu("Add to playlist");

    CONTEXT_MENU.getItems().addAll(addToPlaylist);
  }

  public void setSelectedTrackUri(String trackUri) {
    SELECTED_TRACK_URI = trackUri;
  }

  public ContextMenu getTrackMenu() {

    Menu playlistMenu = (Menu) CONTEXT_MENU.getItems().get(0);
    playlistMenu.getItems().clear();

    Collection<PlaylistSimplified> userOwnedPlaylists = Arrays.stream(cacheHelper.getUserPlaylists().getItems())
        .filter(playlist -> playlist.getOwner().getId().equals(userRepository.getCurrentUser().getId()))
        .collect(Collectors.toList());

    playlistMenu.getItems().addAll(createPlaylistMenuItems(userOwnedPlaylists));

    return CONTEXT_MENU;
  }

  private MenuItem[] createPlaylistMenuItems(Collection<PlaylistSimplified> playlists) {
    Collection<MenuItem> menuItems = new LinkedList<>();

    for (PlaylistSimplified playlist : playlists) {
      MenuItem playlistItem = new MenuItem(playlist.getName());

      playlistItem.setOnAction(event -> {
        try {
          playlistRepository.addTrackToPlaylist(playlist.getId(), SELECTED_TRACK_URI);
          AlertUtil.showInfo(String.format("Track added to %s", playlist.getName()));
        } catch (Exception e) {
          AlertUtil.showGenericServiceError();
        }
      });

      menuItems.add(playlistItem);
    }

    return menuItems.toArray(new MenuItem[menuItems.size()]);
  }
}
