/*
 * GilvanovDR (c) 2020.
 */

/*
 * GilvanovDR (c) 2020.
 */

package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repo.InMemory;
import ru.javawebinar.topjava.repo.RepoInterface;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = getLogger(MealServlet.class);
    private static String INSERT_OR_EDIT = "mealEdit.jsp";
    private static String LIST_MEALS = "meals.jsp";
    RepoInterface repo = InMemory.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal();
        meal.setDescription(request.getParameter("description"));
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        meal.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")));
        String mealId = request.getParameter("Id");
        if (mealId == null || mealId.isEmpty()) {
            repo.add(meal);
        } else {
            meal.setId(Integer.parseInt(mealId));
            repo.update(meal, Integer.parseInt(mealId));
        }
        request.setAttribute("Meals", MealsUtil.filteredByStreams(repo.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
        request.getRequestDispatcher(LIST_MEALS).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String forward;
        String action = request.getParameter("action");
        int mealId = Integer.parseInt(request.getParameter("Id") != null ? request.getParameter("Id") : "0");
        switch (action) {
            case "delete":
                repo.delete(mealId);
                log.debug("delete");
            case "listMeals":
                forward = LIST_MEALS;
                request.setAttribute("Meals", MealsUtil.filteredByStreams(repo.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
                log.debug("listMeals");
                break;
            case "edit":
                forward = INSERT_OR_EDIT;
                Meal meal = repo.getMeal(mealId);
                request.setAttribute("meal", meal);
                log.debug("edit");
                break;
            default:
                forward = INSERT_OR_EDIT;
                log.debug("action no params");
                break;
        }
        request.getRequestDispatcher(forward).forward(request, response);
    }
}

