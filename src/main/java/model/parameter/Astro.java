package model.parameter;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Astro {
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;
    private String moon_phase;
    private String moon_illumination;
}
