/*
 * GilvanovDR (c) 2020.
 */

/*
 * GilvanovDR (c) 2020.
 */
package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class MealTo {
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private final boolean excess;
    private final int id;

    public MealTo(LocalDateTime dateTime, String description, int calories, boolean excess, int id) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getCalories() {
        return String.valueOf(calories);
    }

    public boolean getExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }
}
