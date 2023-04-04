import com.example.Feline;
import com.example.IFeline;
import com.example.Lion;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.junit.Assert.assertThrows;

@RunWith(DataProviderRunner.class)
public class LionTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    Lion lion;

    IFeline feline = new IFeline() {
        @Override
        public List<String> eatMeat() throws Exception {
            return List.of();
        }

        @Override
        public int getKittens() {
            return 1;
        }

        @Override
        public List<String> getFood() throws Exception {
            return List.of();
        }
    };

    @Before
    public void initLion() throws Exception {
        lion = new Lion("Самец", feline);
    }

    @DataProvider
    public static Object[][] generateSexLionEquals() {

        return new Object[][]{

                {"Самец", true}, {"Самка", false}

        };
    }

    @Test
    @UseDataProvider("generateSexLionEquals")
    public void lionConstructorEqualsTest(String sex, boolean expected) throws Exception {

        Lion lion = new Lion(sex, new Feline());
        boolean actual = lion.doesHaveMane();

        Assert.assertEquals(expected, actual);
    }

    @DataProvider
    public static Object[][] generateSexLionNotEquals() {

        return new Object[][]{

                {"Самка", true}, {"Самец", false}, {"Самец", false}, {"Самка", true},};
    }

    @Test
    @UseDataProvider("generateSexLionNotEquals")
    public void lionConstructorNotEqualsTest(String sex, boolean expected) throws Exception {

        Lion lion = new Lion(sex, feline);
        boolean actual = lion.doesHaveMane();

        Assert.assertNotEquals(expected, actual);
    }

    @Test
    public void getExceptionConstructorTest() {
        assertThrows(Exception.class, () -> new Lion("test", feline).doesHaveMane());

    }

    @DataProvider
    public static Object[][] generateKittensNotEquals() {

        return new Object[][]{

                {1, 3}, {20, 10}, {100, 200}};
    }

    @DataProvider
    public static Object[][] generateKittensEquals() {

        return new Object[][]{

                {0, 0}, {20, 20}, {100, 100}};
    }

    @Test
    public void getKittensTestEquals() {
        int actual = lion.getKittens();

        Assert.assertEquals(1, actual);
    }

    @Test
    public void getKittensTestNotEquals() {
        int actual = lion.getKittens();

        Assert.assertNotEquals(2, actual);
    }

    @Test
    public void getFoodTest() throws Exception {
        Lion spyLion = Mockito.spy(lion);

        spyLion.getFood();

        Mockito.verify(spyLion).getFood();
    }
}
