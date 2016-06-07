import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ModelGenerator
{
    public static void main(String[] args) throws Exception
    {
        File modelsDir = new File("models");
        File blockDir = new File(modelsDir, "block");
        File itemDir = new File(modelsDir, "item");
        modelsDir.mkdir();
        blockDir.mkdir();
        itemDir.mkdir();

        if (false)
        generate(
                // DIRECTORY
                blockDir,
                // MODEL
                "{\n" +
                        "\t\"parent\": \"block/cube_all\",\n" +
                        "\t\"textures\": {\n" +
                        "\t\t\"all\": \"genesis:blocks/rubble_${name}\"\n" +
                        "\t}\n" +
                        "}\n",
                // NAMES
                "granite",
                "mossy_granite",
                "rhyolite",
                "dolerite"
        );
        if (true)
        generate(
                // DIRECTORY
                itemDir,
                // MODEL
                "{\n" +
                        "    \"parent\": \"block/wall_inventory\",\n" +
                        "    \"textures\": {\n" +
                        "        \"wall\": \"genesis:blocks/rubble_${name}\"\n" +
                        "    }\n" +
                        "}\n",
                // NAMES
                "granite",
                "mossy_granite",
                "rhyolite",
                "dolerite"
        );
    }

    private static void generate(File dir, String model, String... names) throws Exception
    {
        generate(dir, null, model, names);
    }

    private static void generate(File dir, Map<String, String> substitions, String model, String... names) throws Exception
    {
        for (String name : names)
        {
            HashMap<String, String> substitutionMap = new HashMap<String, String>();
            substitutionMap.put("name", name);
            if (substitions != null)
                substitutionMap.putAll(substitions);
            FileUtils.writeStringToFile(
                    new File(dir, name + ".json"),
                    new StrSubstitutor(substitutionMap).replace(model));
        }
    }
}
