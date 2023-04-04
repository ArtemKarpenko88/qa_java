import com.example.Cat;
import com.example.Feline;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.util.List;

@RunWith(DataProviderRunner.class)

public class CatTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void initCat() {
        cat = new Cat(feline);
    }

    @Spy
    Feline feline;

    Cat cat;

    @DataProvider
    public static Object[][] generateNegativeSound() {

        return new Object[][]{

                {"Гав"},
                {"  Мяу"},
                {"   "}
        };
    }


    @Test
    @UseDataProvider("generateNegativeSound")
    public void getNegativeSoundTest(String NegativeExpected) {

        String actual = cat.getSound();

        Assert.assertNotEquals(NegativeExpected, actual);
    }

    @Test

    public void getSoundTest() {
        String expected = "Мяу";

        String actual = cat.getSound();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getFoodTest() throws Exception {
        List<String> expected = List.of("Животные", "Птицы", "Рыба");

        List<String> actual = cat.getFood();

        Mockito.verify(feline).eatMeat();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNotFoodTest() throws Exception {
        List<String> expected = List.of("Трава", "Различные растения");

        List<String> actual = cat.getFood();

        Assert.assertNotEquals(expected, actual);
    }
}
