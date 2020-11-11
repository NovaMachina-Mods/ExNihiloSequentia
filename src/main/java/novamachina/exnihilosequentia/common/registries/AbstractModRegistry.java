package novamachina.exnihilosequentia.common.registries;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import novamachina.exnihilosequentia.common.utility.Constants;
import novamachina.exnihilosequentia.common.utility.LogUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.List;

public abstract class AbstractModRegistry {
    public abstract void clear();

    public abstract List toJSONReady();

    public abstract void useJson();

    protected boolean generateJson(String fileName, AbstractModRegistry registry) {
        File file = Constants.Json.baseJsonPath.resolve(fileName).toFile();
        if (Files.exists(file.toPath())) {
            registry.clear();
            return false;
        }
        if (!Files.exists(file.toPath())) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<?> list = registry.toJSONReady();
            try (Writer writer = new FileWriter(file)) {
                writer.write(gson.toJson(list));
                LogUtil.info("Created " + fileName);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
