package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        List<UserMealWithExceed> mealWithExceeds = new ArrayList<>();

        Map<LocalDate, Integer> map = mealList.stream().collect(Collectors.toMap(p -> p.getDateTime().toLocalDate(), UserMeal::getCalories, (p, a) -> p + a));

        LocalDate temp = null;
        for (Map.Entry<LocalDate, Integer> key : map.entrySet()) {
            if (key.getValue() > caloriesPerDay) temp = key.getKey();
        }

        for (UserMeal list : mealList) {
            if (list.getDateTime().getDayOfMonth() == temp.getDayOfMonth()) {
                mealWithExceeds.add(new UserMealWithExceed(list.getDateTime(), list.getDescription(), list.getCalories(), true));
            }
        }

        System.out.println(mealWithExceeds);

        return mealWithExceeds;
    }
}