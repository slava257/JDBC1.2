package model;

import lombok.Data;

@Data
public class City {
    private final int city_id;
    private final String city_name;

    public City(int city_id, String city_name) {
        this.city_id = city_id;
        this.city_name = city_name;
    }
}
