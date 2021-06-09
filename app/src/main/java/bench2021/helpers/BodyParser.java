package bench2021.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import bench2021.dto.Page;

/**
 * Wrapper object around gson parsing, largely used for mock injections.
 */
public class BodyParser {
  private Gson gson = new Gson();

  /**
   * Deserializes string body into Page object.
   * @param body Json encoded string representing a Page object.
   * @return Page object deserialized from string body.
   * @throws JsonSyntaxException Throws a JsonSyntaxException if the serialization fails.
   */
  public Page parseJson(String body) throws JsonSyntaxException {
    //This could be generalized to deserialize different types of responses.
    return gson.fromJson(body, Page.class);
  }
}
