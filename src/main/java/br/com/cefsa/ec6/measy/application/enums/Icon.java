package br.com.cefsa.ec6.measy.application.enums;

import java.util.HashMap;
import java.util.Map;

public enum Icon {
  ALBUM_WHITE("album_white"),
  ARROW_DROP_DOWN_WHITE("arrow_drop_down_white"),
  ARROW_DROP_UP_WHITE("arrow_drop_up_white"),
  AUDIOTRACK_WHITE("audiotrack_white"),
  CLOSE_WHITE("close_white"),
  DELETE_WHITE("delete_white"),
  DISC_FULL_WHITE("disc_full_white"),
  EXPAND_LESS_WHITE("expand_less_white"),
  EXPAND_MORE_WHITE("expand_more_white"),
  EXPLICIT_WHITE("explicit_white"),
  FAST_FORWARD_WHITE("fast_forward_white"),
  FAST_REWIND_WHITE("fast_rewind_white"),
  FAVORITE_BORDER_WHITE("favorite_border_white"),
  FAVORITE_WHITE("favorite_white"),
  FIRST_PAGE_WHITE("first_page_white"),
  HISTORY_WHITE("history_white"),
  HOME_WHITE("home_white"),
  LAST_PAGE_WHITE("last_page_white"),
  LOOP_WHITE("loop_white"),
  MENU_WHITE("menu_white"),
  MORE_HORIZONTAL_WHITE("more_horizontal_white"),
  NAVIGATE_BEFORE_BLACK("navigate_before_black"),
  NAVIGATE_BEFORE_WHITE("navigate_before_white"),
  NAVIGATE_NEXT_BLACK("navigate_next_black"),
  NAVIGATE_NEXT_WHITE("navigate_next_white"),
  NEW_WHITE("new_white"),
  PAUSE_CIRCLE_FILLED_WHITE("pause_circle_filled_white"),
  PAUSE_CIRCLE_OUTLINE_WHITE("pause_circle_outline_white"),
  PAUSE_WHITE("pause_white"),
  PERSON_WHITE("person_white"),
  PLAY_ARROW_WHITE("play_arrow_white"),
  PLAY_CIRCLE_FILLED_WHITE("play_circle_filled_white"),
  PLAY_CIRCLE_OUTLINE_WHITE("play_circle_outline_white"),
  PLAYLIST_ADD_CHECK_WHITE("playlist_add_check_white"),
  PLAYLIST_ADD_WHITE("playlist_add_white"),
  PLAYLIST_PLAY_WHITE("playlist_play_white"),
  QUEUE_MUSIC_WHITE("queue_music_white"),
  REFRESH_WHITE("refresh_white"),
  REPEAT_ONE_WHITE("repeat_one_white"),
  REPEAT_WHITE("repeat_white"),
  REPLAY_WHITE("replay_white"),
  SEARCH_BLACK("search_black"),
  SEARCH_WHITE("search_white"),
  SETTINGS_WHITE("settings_white"),
  SHUFFLE_WHITE("shuffle_white"),
  SKIP_NEXT_WHITE("skip_next_white"),
  SKIP_PREVIOUS_WHITE("skip_previous_white"),
  SPOTIFY_LOGO_BLACK("spotify_logo_black"),
  STAR_BORDER_WHITE("star_border_white"),
  STAR_HALF_WHITE("star_half_white"),
  STAR_WHITE("star_white"),
  STOP_WHITE("stop_white"),
  THUMB_DOWN_WHITE("thumb_down_white"),
  THUMB_UP_WHITE("thumb_up_white"),
  TUNE_WHITE("tune_white"),
  VOLUME_DOWN_WHITE("volume_down_white"),
  VOLUME_MUTE_WHITE("volume_mute_white"),
  VOLUME_OFF_WHITE("volume_off_white"),
  VOLUME_UP_WHITE("volume_up_white"),
  WARNING_WHITE("warning_white"),
  YOUTUBE_LOGO_RED("youtube_logo_red");

  private static final Map<String, Icon> map = new HashMap<>();

  static {
    for (Icon icon : Icon.values()) {
      map.put(icon.type, icon);
    }
  }

  public final String type;

  Icon(final String type) {
    this.type = type;
  }

  public static Icon fromKey(String key) {
    return map.get(key);
  }

  public String getType() {
    return this.type;
  }
}
