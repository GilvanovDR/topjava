package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal MEAL : MealsUtil.MEALS) {
            save(MEAL, MEAL.getOwnerId());
        }
    }

    @Override
    public Meal save(Meal meal, Integer userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        if (!isOwnerMeal(userId, meal)) {
            return null;
        }
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, Integer userId) {
        Meal meal = repository.get(id);
        if (!isOwnerMeal(userId, meal)) {
            return false;
        }
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, Integer userId) {
        Meal meal = repository.get(id);
        if (isOwnerMeal(userId, meal)) {
            return meal;
        }
        return null;
    }

    @Override
    public Collection<Meal> getAll(Integer userId) {
        return repository.values().stream().filter(meal -> meal.getOwnerId().equals(userId)).collect(Collectors.toList());
    }

    private boolean isOwnerMeal(Integer userId, Meal meal) {
        return meal != null && meal.getOwnerId().equals(userId);
    }
}

