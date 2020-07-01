package world.bentobox.bentobox.blueprints.worldedit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;

import world.bentobox.bentobox.BentoBox;

/**
 * @since 1.6.0
 * @author CustomEntity
 */
public class BlueprintSchematicConverter {

    private File blueprintFile;

    public BlueprintSchematicConverter(File blueprintFile) {
        if(!BentoBox.getInstance().getHooks().getHook("WorldEdit").isPresent()) {
            BentoBox.getInstance().logError("WorldEdit 未安装!");
            return;
        }
        this.blueprintFile = blueprintFile;
    }

    public Clipboard convertBlueprintToSchematic() {
        Clipboard clipboard = null;
        try {
            clipboard = ClipboardFormats.findByFile(blueprintFile).getReader(new FileInputStream(blueprintFile)).read();
        } catch (IOException e) {
            BentoBox.getInstance().logWarning("转换蓝图至 schematic 时出错.");
            BentoBox.getInstance().logStacktrace(e);
        }
        return clipboard;
    }

    public File getBlueprintFile() {
        return blueprintFile;
    }
}
