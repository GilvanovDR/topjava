package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private MealRepository repository;

    @Test
    public void get() {
        Meal userMeal = service.get(USER_MEAL_ID_1, USER_ID);
        assertMealMather(USER_MEAL_1, userMeal);
        Meal adminMeal = service.get(ADMIN_MEAL_ID_1, ADMIN_ID);
        assertMealMather(ADMIN_MEAL_1, adminMeal);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, USER_ID));
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, NOT_FOUND));
    }

    @Test
    public void getNotOwned() {
        assertThrows(NotFoundException.class, () -> service.get(ADMIN_MEAL_ID_1, USER_ID));
        assertThrows(NotFoundException.class, () -> service.get(USER_MEAL_ID_2, ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(USER_MEAL_ID_3, USER_ID);
        assertNull(repository.get(USER_MEAL_ID_3, USER_ID));
    }

    @Test
    public void deleteNotOwned() {
        assertThrows(NotFoundException.class, () -> service.delete(USER_MEAL_ID_3, ADMIN_ID));
        assertMealMather(USER_MEAL_3, repository.get(USER_MEAL_ID_3, USER_ID));
    }

    @Test

    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
    }

    @Test
    public void getAll() {
        System.out.println();
        assertMealMather(service.getAll(USER_ID), getUserMeals());
        assertMealMather(service.getAll(ADMIN_ID), getAdminMeals());
    }

    @Test
    public void update() {
        Meal updated = getUpdatedUserMeal1();
        service.update(updated, USER_ID);
        assertMealMather(updated, service.get(updated.getId(), USER_ID));
    }

    @Test
    public void updateNotFound() {
        Meal updated = getUpdatedUserMeal1();
        updated.setId(NOT_FOUND);
        assertThrows(NotFoundException.class, () -> service.update(updated, USER_ID));
    }

    @Test
    public void updateNotOwned() {
        Meal updated = getUpdatedUserMeal1();
        assertThrows(NotFoundException.class,
                () -> service.update(updated, ADMIN_ID));
        assertMealMather(USER_MEAL_1, service.get(updated.getId(), USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        assertMealMather(service.getBetweenInclusive(null, null, USER_ID), getUserMeals());
        assertMealMather(service.getBetweenInclusive(LocalDate.of(2020, 1, 31), null, USER_ID), getFilteredUserMeals());
    }

    @Test
    public void create() {
        Meal newMeal = getNew();
        Meal created = service.create(newMeal, ADMIN_ID);
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertMealMather(newMeal, created);
        assertMealMather(service.get(newId, ADMIN_ID), newMeal, "dateTime");

    }
}