package pl.jellysoft.kodbot.controller.bean;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MapGalleryDTO implements Comparable<MapGalleryDTO> {

    private final int id;
    private final String gameUrl;
    private final String imageUrl;
    private final Integer userBatteryLevel;

    @Override
    public int compareTo(MapGalleryDTO o) {
        return Integer.compare(id, o.id);
    }

}
