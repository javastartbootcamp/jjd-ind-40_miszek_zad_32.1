package pl.javastart.streamstask;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class StreamsTaskTest {

    private StreamsTask streamsTask = new StreamsTask();
    private List<User> users = new ArrayList<>();
    private List<Expense> expenses = new ArrayList<>();

    public StreamsTaskTest() {
        users.add(new User(1L, "Alicja", 20));
        users.add(new User(2L, "Dominik", 15));
        users.add(new User(3L, "Patrycja", 25));
        users.add(new User(4L, "Marcin", 30));
        users.add(new User(5L, "Tomek", 18));
        users.add(new User(6L, "Damian", 26));

        expenses.add(new Expense(1L, "Buty", new BigDecimal("149.99"), ExpenseType.WEAR));
        expenses.add(new Expense(1L, "Sałatka", new BigDecimal("14.99"), ExpenseType.FOOD));
        expenses.add(new Expense(2L, "Bluza", new BigDecimal("100"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Skarpetki", new BigDecimal("39"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Pizza", new BigDecimal("25"), ExpenseType.FOOD));
    }

    @Test
    void shouldContain2Women() {
        Collection<User> women = streamsTask.findWomen(users);
        assertEquals(2 , women.size());
    }

    @Test
    void shouldCountAverageMenAge() {
        Double averageMenAge = streamsTask.averageMenAge(users);
        assertEquals(Double.valueOf(22.25), averageMenAge);
    }

    @Test
    void shouldGroupExpensesByUserId() {
        Map<Long, List<Expense>> expensesGroupedByUserId = streamsTask.groupExpensesByUserId(users, expenses);
        assertEquals(2, expensesGroupedByUserId.get(1L).size());
        assertEquals(3, expensesGroupedByUserId.get(2L).size());
    }

    @Test
    void shouldGroupExpensesByUser() {
        Map<User, List<Expense>> expensesGroupedByUser = streamsTask.groupExpensesByUser(users, expenses);
        User user1 = users.get(0);
        User user2 = users.get(1);
        assertEquals(2, expensesGroupedByUser.get(user1).size());
        assertEquals(3, expensesGroupedByUser.get(user2).size());
    }
}