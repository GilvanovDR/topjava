package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int NOT_FOUND = 10;

    public static final int USER_MEAL_ID_1 = START_SEQ + 2;
    public static final int USER_MEAL_ID_2 = START_SEQ + 3;
    public static final int USER_MEAL_ID_3 = START_SEQ + 4;
    public static final int USER_MEAL_ID_4 = START_SEQ + 5;
    public static final int USER_MEAL_ID_5 = START_SEQ + 6;
    public static final int USER_MEAL_ID_6 = START_SEQ + 7;
    public static final int USER_MEAL_ID_7 = START_SEQ + 8;
    public static final int ADMIN_MEAL_ID_1 = START_SEQ + 9;
    public static final int ADMIN_MEAL_ID_2 = START_SEQ + 10;

    public static final Meal USER_MEAL_1 = new Meal(USER_MEAL_ID_1, LocalDateTime.of(2020, 1, 30, 10, 0), "Завтрак", 500);
    public static final Meal USER_MEAL_2 = new Meal(USER_MEAL_ID_2, LocalDateTime.of(2020, 1, 30, 13, 0), "Обед", 1000);
    public static final Meal USER_MEAL_3 = new Meal(USER_MEAL_ID_3, LocalDateTime.of(2020, 1, 30, 20, 0), "Ужин", 500);
    public static final Meal USER_MEAL_4 = new Meal(USER_MEAL_ID_4, LocalDateTime.of(2020, 1, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal USER_MEAL_5 = new Meal(USER_MEAL_ID_5, LocalDateTime.of(2020, 1, 31, 10, 0), "Завтрак", 500);
    public static final Meal USER_MEAL_6 = new Meal(USER_MEAL_ID_6, LocalDateTime.of(2020, 1, 31, 13, 0), "Обед", 1000);
    public static final Meal USER_MEAL_7 = new Meal(USER_MEAL_ID_7, LocalDateTime.of(2020, 1, 31, 20, 0), "Ужин", 510);
    public static final Meal ADMIN_MEAL_1 = new Meal(ADMIN_MEAL_ID_1, LocalDateTime.of(2020, 1, 31, 14, 0), "Админ ланч", 510);
    public static final Meal ADMIN_MEAL_2 = new Meal(ADMIN_MEAL_ID_2, LocalDateTime.of(2020, 1, 31, 21, 0), "Админ ужин", 1500);


    public static Meal getNew() {
        return new Meal(null, LocalDateTime.now(), "new food", 1000);
    }

    public static Meal getUpdatedUserMeal1() {
        Meal updated = new Meal(USER_MEAL_1);
        updated.setDescription("UpdatedDescription");
        updated.setCalories(1000);
        return updated;
    }

    public static List<Meal> getUserMeals() {
        return Arrays.asList(USER_MEAL_7, USER_MEAL_6, USER_MEAL_5, USER_MEAL_4, USER_MEAL_3, USER_MEAL_2, USER_MEAL_1);
    }

    public static List<Meal> getFilteredUserMeals() {
        return Arrays.asList(USER_MEAL_7, USER_MEAL_6, USER_MEAL_5, USER_MEAL_4);
    }

    public static List<Meal> getAdminMeals() {
        return Arrays.asList(ADMIN_MEAL_2, ADMIN_MEAL_1);
    }

    public static void assertMealMather(Meal actual, Meal expected, String... field) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, field);
    }

    public static void assertMealMather(List<Meal> actual, List<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields().isEqualTo(expected);
    }
}
