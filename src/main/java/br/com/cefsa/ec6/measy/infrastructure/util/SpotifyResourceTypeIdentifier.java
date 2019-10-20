package br.com.cefsa.ec6.measy.infrastructure.util;

import com.wrapper.spotify.model_objects.specification.Track;

public class SpotifyResourceTypeIdentifier {

    public static Boolean isTrackUri(String uri) {
        return isOfType(uri, Track.class);
    }

    private static Boolean isOfType(String uri, Class clazz) {
        return uri.startsWith(String.format("spotify:%s", clazz.getSimpleName().toLowerCase()));
    }
}
