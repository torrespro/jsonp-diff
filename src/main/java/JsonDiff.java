import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonMergePatch;
import javax.json.JsonPatch;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

public class JsonDiff {

    public static void main(String[] args) {

        String leftJsonDocument = """
        {
          "name": {
            "first": "John",
            "last": "Doe"
          },
          "address": null,
          "birthday": "1980-01-01",
          "company": "Acme",
          "occupation": "Software engineer",
          "phones": [
            {
              "number": "000000000",
              "type": "home"
            },
            {
              "number": "999999999",
              "type": "mobile"
            }
          ]
        }
        """;

    String rightJsonDocument = """
        {
          "name": {
            "first": "Jane",
            "last": "Doe",
            "nickname": "Jenny"
          },
          "birthday": "1990-01-01",
          "occupation": null,
          "phones": [
            {
              "number": "111111111",
              "type": "mobile"
            }
          ],
          "favorite": true,
          "groups": [
            "close-friends",
            "gym"
          ]
        }
        """;

        JsonValue source = Json.createReader(new StringReader(leftJsonDocument)).readValue();
        JsonValue target = Json.createReader(new StringReader(rightJsonDocument)).readValue();

        JsonPatch diff = Json.createDiff(source.asJsonObject(), target.asJsonObject());
        System.out.println("Producing a JSON Patch document with the differences");
        System.out.println(format(diff.toJsonArray()));

        JsonMergePatch mergeDiff = Json.createMergeDiff(source, target);
        System.out.println("Producing a JSON Merge Patch document with the differences");
        System.out.println(format(mergeDiff.toJsonValue()));

    }

    public static String format(JsonValue jsonValue) {
        StringWriter stringWriter = new StringWriter();
        prettyPrint(jsonValue, stringWriter);
        return stringWriter.toString();
    }

    public static void prettyPrint(JsonValue jsonValue, Writer writer) {
        Map<String, Object> config = Collections.singletonMap(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(config);
        try (JsonWriter jsonWriter = writerFactory.createWriter(writer)) {
            jsonWriter.write(jsonValue);
        }
    }

}
