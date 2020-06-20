package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal, Integer authUserID) {
        return repository.save(meal, authUserID);
    }

    public void delete(int id, Integer authUserID) {
        checkNotFoundWithId(repository.delete(id, authUserID), id);
    }

    public Meal get(int id, Integer authUserID) {
        return checkNotFoundWithId(repository.get(id, authUserID), id);
    }

    public Collection<Meal> getAll(Integer authUserID) {
        return repository.getAll(authUserID);
    }

    public void update(Meal meal, Integer authUserID) {
        checkNotFoundWithId(repository.save(meal, authUserID), meal.getId());
    }

}