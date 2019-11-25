package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.application.enums.Icon;
import br.com.cefsa.ec6.measy.domain.playback.SpotifyPlayer;
import br.com.cefsa.ec6.measy.infrastructure.definitions.spotify.LoopMode;
import br.com.cefsa.ec6.measy.infrastructure.util.IconURLFinder;
import br.com.cefsa.ec6.measy.infrastructure.util.ImagePicker;
import br.com.cefsa.ec6.measy.infrastructure.util.LoopModeHelper;
import br.com.cefsa.ec6.measy.infrastructure.util.formatter.TrackFormatter;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PlayerPanelController {

  @Autowired private SpotifyPlayer spotifyPlayer;
  @Autowired private IconURLFinder iconURLFinder;

  @FXML private Button previous;
  @FXML private Button next;
  @FXML private Button playPause;
  @FXML private Button shuffle;
  @FXML private Button loop;
  @FXML private ImageView playPauseImage;
  @FXML private ImageView shuffleImage;
  @FXML private ImageView loopImage;
  @FXML private ImageView trackImage;
  @FXML private Label trackName;

  @FXML
  private void onMouseClickedPrevious(MouseEvent event) {
    previousTrack();
    refresh();
  }

  @FXML
  private void onMouseClickedPlayPause(MouseEvent event) {
    playPause();
    refresh();
  }

  @FXML
  private void onMouseClickedNext(MouseEvent event) {
    nextTrack();
    refresh();
  }

  @FXML
  private void onMouseClickedShuffle(MouseEvent event) {
    changeShuffle();
    refresh();
  }

  @FXML
  private void onMouseClickedLoop(MouseEvent event) {
    changeLoop();
    refresh();
  }

  private void nextTrack() {
    spotifyPlayer.nextTrack();
  }

  private void previousTrack() {
    spotifyPlayer.previousTrack();
  }

  private void playPause() {
    if (spotifyPlayer.isPlaying()) spotifyPlayer.pause();
    else spotifyPlayer.resume();
  }

  private void changeShuffle() {
    spotifyPlayer.setShuffle(!spotifyPlayer.getShuffle());
  }

  private void changeLoop() {
    spotifyPlayer.setLoop(LoopModeHelper.getNextLoopMode(spotifyPlayer.getLoop()));
  }

  public void refresh() {
    final CurrentlyPlayingContext playbackInfo = spotifyPlayer.getPlaybackInfo();

    playPauseImage.setImage(
        new Image(
            iconURLFinder.findRelativePath(
                playbackInfo.getIs_playing() ? Icon.PAUSE_WHITE : Icon.PLAY_ARROW_WHITE)));

    trackImage.setImage(
        new Image(
            ImagePicker.pickHighestResolutionImage(playbackInfo.getItem().getAlbum().getImages())
                .getUrl()));

    loopImage.setImage(
        new Image(
            iconURLFinder.findRelativePath(
                LoopModeHelper.getIcon(LoopMode.getByKey(playbackInfo.getRepeat_state())))));

    shuffleImage.setImage(
        new Image(
            iconURLFinder.findRelativePath(
                playbackInfo.getShuffle_state() ? Icon.SHUFFLE_WHITE : Icon.SHUFFLE_WHITE)));

    trackName.setText(TrackFormatter.formatTrackTitle(playbackInfo.getItem()));
  }
}
