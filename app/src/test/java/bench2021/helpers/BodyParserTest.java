package bench2021.helpers;

import static org.junit.Assert.assertThrows;

import com.google.gson.JsonSyntaxException;

import org.junit.Before;
import org.junit.Test;

public class BodyParserTest {

  BodyParser bodyParser;

  @Before
  public void setUp() {
    bodyParser = new BodyParser();
  }

  @Test
  public void testParseJson() throws JsonSyntaxException {
    assertThrows(JsonSyntaxException.class, () -> bodyParser.parseJson("aksdjfhakg"));
  }
}