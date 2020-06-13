/*
 * GilvanovDR (c) 2020.
 */

/*
 * GilvanovDR (c) 2020.
 */

package ru.javawebinar.topjava.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class InMemory implements RepoInterface {
    private static final Logger log = LoggerFactory.getLogger(InMemory.class);
    private static InMemory inMemory;
    private static AtomicInteger counter = new AtomicInteger(0);
    private Map<Integer, Meal> meals = new ConcurrentHashMap<>();

    private InMemory() {
        for (Meal meal : MealsUtil.meals) {
            int id = counter.incrementAndGet();
            meal.setId(id);
            meals.put(id, meal);
        }
    }

    //Singletone
    public static InMemory getInstance() {
        if (inMemory == null) {
            inMemory = new InMemory();
        }
        return inMemory;
    }


    @Override
    public List<Meal> getAll() {
        log.debug("get all from repo");
        return new ArrayList<>(meals.values());

    }

    @Override
    public int add(Meal meal) {
        int id = counter.incrementAndGet();
        meal.setId(id);
        meals.put(id, meal);
        log.debug("add " + meal + " to repo");
        return id;
    }

    @Override
    public void delete(int mealId) {
        log.debug("remove id=" + mealId + "from repo");
        meals.remove(mealId);
    }

    @Override
    public int update(Meal meal, int mealId) {
        meals.put(mealId, meal);
        log.debug("update meal at repo");
        return mealId;
    }

    @Override
    public Meal getMeal(int mealId) {
        log.debug("get meal from repo");
        return meals.get(mealId);
    }
}
