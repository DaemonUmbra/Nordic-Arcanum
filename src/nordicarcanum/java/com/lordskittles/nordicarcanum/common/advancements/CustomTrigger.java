package com.lordskittles.nordicarcanum.common.advancements;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.lordskittles.nordicarcanum.core.NordicArcanum.RL;

public class CustomTrigger implements ICriterionTrigger
{
    private final ResourceLocation ID;
    private final Map<PlayerAdvancements, CustomTrigger.Listeners> listeners = Maps.newHashMap();

    public CustomTrigger(String name)
    {
        super();
        this.ID = RL(name);
    }

    @Override
    public ResourceLocation getId()
    {
        return this.ID;
    }

    @Override
    public void addListener(PlayerAdvancements advancements, Listener listener)
    {
        CustomTrigger.Listeners listeners = this.listeners.computeIfAbsent(advancements, Listeners::new);
        listeners.add(listener);
    }

    @Override
    public void removeListener(PlayerAdvancements advancements, Listener listener)
    {
        CustomTrigger.Listeners listeners = this.listeners.get(advancements);

        if (listeners != null)
        {
            listeners.remove(listener);

            if (listeners.isEmpty())
            {
                this.listeners.remove(advancements);
            }
        }
    }

    @Override
    public void removeAllListeners(PlayerAdvancements advancements)
    {
        this.listeners.remove(advancements);
    }

    @Override
    public ICriterionInstance deserialize(JsonObject object, ConditionArrayParser conditions)
    {
        return new CustomTrigger.Instance(this.getId());
    }

    public void trigger(ServerPlayerEntity parPlayer)
    {
        CustomTrigger.Listeners listeners = this.listeners.get(parPlayer.getAdvancements());

        if (listeners != null)
        {
            listeners.trigger(parPlayer);
        }
    }

    public static class Instance extends CriterionInstance
    {
        /**
         * Instantiates a new instance.
         */
        public Instance(ResourceLocation parID)
        {
            super(parID, EntityPredicate.AndPredicate.ANY_AND);
        }

        /**
         * Test.
         *
         * @return true, if successful
         */
        public boolean test()
        {
            return true;
        }
    }

    static class Listeners
    {
        private final PlayerAdvancements playerAdvancements;
        private final Set<Listener<Instance>> listeners = Sets.newHashSet();

        /**
         * Instantiates a new listeners.
         *
         * @param playerAdvancementsIn the player advancements in
         */
        public Listeners(PlayerAdvancements playerAdvancementsIn)
        {
            this.playerAdvancements = playerAdvancementsIn;
        }

        /**
         * Checks if is empty.
         *
         * @return true, if is empty
         */
        public boolean isEmpty()
        {
            return this.listeners.isEmpty();
        }

        /**
         * Adds the.
         *
         * @param listener the listener
         */
        public void add(ICriterionTrigger.Listener listener)
        {
            this.listeners.add(listener);
        }

        /**
         * Removes the.
         *
         * @param listener the listener
         */
        public void remove(ICriterionTrigger.Listener listener)
        {
            this.listeners.remove(listener);
        }

        /**
         * Trigger.
         *
         * @param player the player
         */
        public void trigger(ServerPlayerEntity player)
        {
            List<ICriterionTrigger.Listener<CustomTrigger.Instance>> list = null;

            for (ICriterionTrigger.Listener<CustomTrigger.Instance> listener : this.listeners)
            {
                if (listener.getCriterionInstance().test())
                {
                    if (list == null)
                    {
                        list = Lists.newArrayList();
                    }

                    list.add(listener);
                }
            }

            if (list != null)
            {
                for (ICriterionTrigger.Listener listener1 : list)
                {
                    listener1.grantCriterion(this.playerAdvancements);
                }
            }
        }
    }
}
