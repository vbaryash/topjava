package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args)
    {

        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );


        getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);


    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        boolean mealExceed30 = false;
        boolean mealExceed31 = false;

        LocalDate mealDate;
        LocalTime mealTime;
        int mealCalories;

        int caloriesSum30 = 0;
        int caloriesSum31 = 0;

        List <UserMealWithExceed> mealWithExceeds = new ArrayList<>();


        for(UserMeal userMealCalories : mealList)
        {
            mealDate = userMealCalories.getDateTime().toLocalDate();
            mealCalories = userMealCalories.getCalories();

            if(mealDate.compareTo(LocalDate.of(2015, Month.MAY, 30)) == 0 ) {
                caloriesSum30 += mealCalories;

                if(caloriesSum30>caloriesPerDay)
                    mealExceed30 = true;
            }
            else
            if(mealDate.compareTo(LocalDate.of(2015, Month.MAY, 31)) ==0 ){
                caloriesSum31 += mealCalories;

                if(caloriesSum31>caloriesPerDay)
                    mealExceed31 = true;
            }
        }


    for(UserMeal userMeal : mealList)
    {
        mealDate = userMeal.getDateTime().toLocalDate();
        mealTime = userMeal.getDateTime().toLocalTime();


        if(mealDate.compareTo(LocalDate.of(2015, Month.MAY, 30)) == 0 ) {

            if ((mealTime.compareTo(startTime) >= 0 && mealTime.compareTo(endTime) <= 0)) {

                System.out.println("КАЛОРИИ ЗА 30 МАЯ -> " + caloriesSum30 + " | 30го ПРЕВЫШЕНА НОРМА -> " + mealExceed30);

                mealWithExceeds.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), mealExceed30));

            }
        }
        else
        if(mealDate.compareTo(LocalDate.of(2015, Month.MAY, 31)) ==0 ){


            if ((mealTime.compareTo(startTime) >= 0 && mealTime.compareTo(endTime) <= 0)) {

                System.out.println("КАЛОРИИ ЗА 31 МАЯ -> " + caloriesSum31 + " | 31го ПРЕВЫШЕНА НОРМА -> " + mealExceed31);

                mealWithExceeds.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), mealExceed31));

            }
        }
    }




        for(UserMealWithExceed withExceeds : mealWithExceeds)
            System.out.println("ДАТА -> "+withExceeds.getDateTime().toLocalDate() + " ВРЕМЯ -> "+withExceeds.getDateTime().toLocalTime() +" ОПИСАНИЕ-> "+withExceeds.getDescription()+" КАЛОРИИ -> "+withExceeds.getCalories()+ " ПРЕВЫШЕНА НОРМА ЗА ДЕНЬ -> "+withExceeds.getExceed());



        return mealWithExceeds;
    }
}
