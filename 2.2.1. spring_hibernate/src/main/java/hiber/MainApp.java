package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

// 4. Создайте несколько пользователей с машинами, добавьте их в базу данных, вытащите обратно.
        User user5 = new User("Tom", "Black", "www.user5");
        User user6 = new User("Kate", "White", "www.user6");
        User user7 = new User("Andrew", "Yellow", "www.user7");

        Car car5 = new Car("BMW", 5);
        Car car6 = new Car("BMW", 6);
        Car car7 = new Car("BMW", 7);

        user5.setCar(car5);
        user6.setCar(car6);
        user7.setCar(car7);

        userService.add(user5);
        userService.add(user6);
        userService.add(user7);

        User userHasCar8 = userService.getUserByCarModelAndSeries("BMW", 8);
        System.out.println(userHasCar8.toString());

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        context.close();
    }
}
