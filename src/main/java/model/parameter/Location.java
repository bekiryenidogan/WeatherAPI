package model.parameter;

import lombok.Getter;
import lombok.Setter;

import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class Location {
    @NotNull("city name can not be null")
    private String name;
    private String region;
    private String country;
    private double lat;
    private double lon;
    private String tz_id;
    private int localtime_epoch;
    private String localtime;
}
