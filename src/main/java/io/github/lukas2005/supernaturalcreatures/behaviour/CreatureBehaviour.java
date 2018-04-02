package io.github.lukas2005.supernaturalcreatures.behaviour;

import io.github.lukas2005.supernaturalcreatures.capabilities.IPlayerDataCapability;
import io.github.lukas2005.supernaturalcreatures.enums.BufType;
import io.github.lukas2005.supernaturalcreatures.enums.CreatureType;
import io.github.lukas2005.supernaturalcreatures.enums.ResistanceLevel;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public interface CreatureBehaviour {

	boolean shouldApplyBuf(BufType buf, EntityPlayer player, IPlayerDataCapability playerData, CreatureType type);

	ResistanceLevel getResistanceLevel(DamageSource source);
	void applyWeakness(LivingHurtEvent hurtEvent, IPlayerDataCapability playerData, CreatureType type, EntityPlayer player);

	void playerSkinOverlayRender(IPlayerDataCapability playerData, AbstractClientPlayer player, RenderPlayer playerRenderer, float scale, float partialTicks);
	void playerRenderOverride(IPlayerDataCapability playerData, EntityPlayer player, RenderLivingBase<?> renderer);

}
