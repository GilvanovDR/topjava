/*
 * GilvanovDR (c) 2020.
 */

/*
 * GilvanovDR (c) 2020.
 */

package ru.javawebinar.topjava.repo;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface RepoInterface {
    List<Meal> getAll();

    int add(Meal meal);

    void delete(int mealId);

    int update(Meal meal, int mealId);

    Meal getMeal(int mealId);
}
