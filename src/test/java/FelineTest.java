import com.example.Feline;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

@RunWith(DataProviderRunner.class)
public class FelineTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void initFeline() {
        feline = new Feline();
    }

    @Spy
    Feline feline;

    @DataProvider
    public static Object[][] generateNumberOfKittensNotEquals() {

        return new Object[][]{

                {0, 3}, {20, 10}, {100, 200}};
    }

    @Test
    @UseDataProvider("generateNumberOfKittensNotEquals")
    public void getKittensTestNotEquals(int expected, int negativeExpected) {

        int actual = feline.getKittens(negativeExpected);

        Assert.assertNotEquals(expected, actual);

    }

    @DataProvider
    public static Object[][] generateNumberOfKittens() {

        return new Object[][]{

                {0, 0}, {20, 20}, {100, 100}};
    }

    @Test
    @UseDataProvider("generateNumberOfKittens")
    public void getKittensTestEquals(int positiveExpected, int expected) {

        int actual = feline.getKittens(positiveExpected);


        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getKittensTest() {
        int expected = 1;

        int actual = feline.getKittens();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPositiveFamilyTest() {
        String positiveExpected = "Кошачьи";

        String actual = feline.getFamily();

        Assert.assertEquals(positiveExpected, actual);
    }

    @Test
    public void getNegativeFamilyTest() {
        String negativeExpected = "Собака";

        String actual = feline.getFamily();


        Assert.assertNotEquals(negativeExpected, actual);

    }

    @Test
    public void eatMeatTest() throws Exception {
        List<String> expected = List.of("Животные", "Птицы", "Рыба");

        List<String> actual = feline.eatMeat();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getFoodTest() throws Exception {
        Feline spyFeline = Mockito.spy(feline);

        spyFeline.getFood();

        Mockito.verify(spyFeline, Mockito.times(1)).getFood();
    }
}
