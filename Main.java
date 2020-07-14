package StreamAPI.SecondTask;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Collection<People> peoples = Arrays.asList(
                new People("Вася", 16, Sex.MAN),
                new People("Петя", 23, Sex.MAN),
                new People("Елена", 42, Sex.WOMEN),
                new People("Иван Иванович", 69, Sex.MAN)
        );

        Stream<People> stream = peoples.stream();
        List<People> list = stream.filter(s -> s.getSex().equals(Sex.MAN))
                .filter(s -> s.getAge() > 18 && s.getAge() < 27)
                .collect(Collectors.toList());
        System.out.println(list);

        //сделал проверку не пустое ли значение у переменой которая хранит в себе мужчин,
        // чтобы не было исключения в случае выборки одних женщин
        OptionalDouble averageMan = peoples.stream().filter(s -> s.getSex().equals(Sex.MAN))
                .mapToInt(People::getAge)
                .average();
        if (averageMan.isPresent()) {
            System.out.println(averageMan.getAsDouble());
        }

        /*
        Double averageManAge = stream2.filter(s -> s.getSex().equals(Sex.MAN))
                .mapToInt(People::getAge)
                .average()
                .getAsDouble();
        System.out.println(averageManAge);
         */

        Stream<People> stream3 = peoples.stream();
        Long potentiallyWorkingPeople = stream3.filter(s -> s.getSex().equals(Sex.MAN) ?
                s.getAge() > 18 && s.getAge() < 65 :
                s.getAge() > 18 && s.getAge() < 60)
                .count();
        System.out.println(potentiallyWorkingPeople);
    }
}
