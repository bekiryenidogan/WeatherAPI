package model.daily;

import lombok.Getter;
import lombok.Setter;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

@Getter
@Setter
public class DailyRoot {
    private Coord coord;
    private ArrayList<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private int dt;
    private Sys sys;
    private int  timezone;
    private int id;

    @NotNull("city name can not be null")
    private String name;
    private int cod;
}
