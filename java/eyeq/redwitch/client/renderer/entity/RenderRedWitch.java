package eyeq.redwitch.client.renderer.entity;

import eyeq.util.client.renderer.EntityRenderResourceLocation;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWitch;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.util.ResourceLocation;

import static eyeq.redwitch.RedWitch.MOD_ID;

public class RenderRedWitch extends RenderWitch {
    protected static final ResourceLocation textures = new EntityRenderResourceLocation(MOD_ID, "red_witch");

    public RenderRedWitch(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWitch entity) {
        return textures;
    }
}
