package model.parameter;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Forecast {
    private ArrayList<Forecastday> forecastday;

}
