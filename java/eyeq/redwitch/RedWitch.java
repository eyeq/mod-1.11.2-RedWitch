package eyeq.redwitch;

import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import eyeq.util.common.registry.UEntityRegistry;
import eyeq.util.world.biome.BiomeUtils;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import eyeq.redwitch.entity.monster.EntityRedWitch;
import eyeq.redwitch.client.renderer.entity.RenderRedWitch;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.util.List;

import static eyeq.redwitch.RedWitch.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
public class RedWitch {
    public static final String MOD_ID = "eyeq_redwitch";

    @Mod.Instance(MOD_ID)
    public static RedWitch instance;

    private static final ResourceLocationFactory resource = new ResourceLocationFactory(MOD_ID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        registerEntities();
        if(event.getSide().isServer()) {
            return;
        }
        registerEntityRenderings();
        createFiles();
    }

    public static void registerEntities() {
        UEntityRegistry.registerModEntity(resource, EntityRedWitch.class, "RedWitch", 0, instance, 0x4D141E, 0x2E4E2C);
        List<Biome> biomes = BiomeUtils.getSpawnBiomes(EntityWitch.class, EnumCreatureType.MONSTER);
        EntityRegistry.addSpawn(EntityRedWitch.class, 10, 1, 1, EnumCreatureType.MONSTER, biomes.toArray(new Biome[0]));
    }

    @SideOnly(Side.CLIENT)
    public static void registerEntityRenderings() {
        RenderingRegistry.registerEntityRenderingHandler(EntityRedWitch.class, RenderRedWitch::new);
    }

    public static void createFiles() {
        File project = new File("../1.11.2-RedWitch");

        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, EntityRedWitch.class, "Red Stone Witch");
        language.register(LanguageResourceManager.JA_JP, EntityRedWitch.class, "赤石の魔女");

        ULanguageCreator.createLanguage(project, MOD_ID, language);
    }
}
