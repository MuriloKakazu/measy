package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.infrastructure.builder.FXMLLoaderBuilder;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TrackCollectionController {

    @FXML
    private CollectionController tracksController;

    public void addTrack(Track track) {
        final FXMLLoader trackRow = appendTrack();
        final TrackRowController trackRowController = trackRow.getController();

        trackRowController.setTrack(track);
    }

    public void addTrack(TrackSimplified track) {
        final FXMLLoader trackRow = appendTrack();
        final TrackRowController trackRowController = trackRow.getController();

        trackRowController.setTrack(track);
    }

    private FXMLLoader appendTrack() {
        try {

            final FXMLLoader trackRow = new FXMLLoaderBuilder()
                    .springManaged()
                    .withFileName("refactored/TrackRow.fxml")
                    .build();

            final Parent trackRowNode = trackRow.load();
            tracksController.addChild(trackRowNode);

            return trackRow;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
