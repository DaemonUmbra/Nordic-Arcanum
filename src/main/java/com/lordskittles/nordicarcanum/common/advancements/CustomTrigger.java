package com.lordskittles.nordicarcanum.common.advancements;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.lordskittles.nordicarcanum.core.NordicArcanum.RL;

public class CustomTrigger implements CriterionTrigger<CustomTrigger>, CriterionTriggerInstance {

    private final ResourceLocation ID;
    private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

    public CustomTrigger(String name) {

        super();
        this.ID = RL(name);
    }

    @Override
    public ResourceLocation getId() {

        return this.ID;
    }

    @Override
    public void addPlayerListener(PlayerAdvancements advancements, Listener<CustomTrigger> listener) {

        CustomTrigger.Listeners listeners = this.listeners.computeIfAbsent(advancements, Listeners::new);
        listeners.add(listener);
    }

    @Override
    public void removePlayerListener(PlayerAdvancements advancements, Listener<CustomTrigger> listener) {

        CustomTrigger.Listeners listeners = this.listeners.get(advancements);

        if(listeners != null) {
            listeners.remove(listener);

            if(listeners.isEmpty()) {
                this.listeners.remove(advancements);
            }
        }
    }

    @Override
    public void removePlayerListeners(PlayerAdvancements advancements) {

        this.listeners.remove(advancements);
    }

    @Override
    public CustomTrigger createInstance(JsonObject jsonObject, DeserializationContext context) {

        return new CustomTrigger("trigger");
    }

    public void trigger(ServerPlayer parPlayer) {

        CustomTrigger.Listeners listeners = this.listeners.get(parPlayer.getAdvancements());

        if(listeners != null) {
            listeners.trigger(parPlayer);
        }
    }

    @Override
    public ResourceLocation getCriterion() {

        return null;
    }

    @Override
    public JsonObject serializeToJson(SerializationContext p_14485_) {

        return null;
    }

    public static class Instance extends AbstractCriterionTriggerInstance {

        /**
         * Instantiates a new instance.
         */
        public Instance(ResourceLocation parID) {

            super(parID, EntityPredicate.Composite.ANY);
        }

        /**
         * Test.
         *
         * @return true, if successful
         */
        public boolean test() {

            return true;
        }
    }

    static class Listeners {

        private final PlayerAdvancements playerAdvancements;
        private final Set<Listener<Instance>> listeners = Sets.newHashSet();

        /**
         * Instantiates a new listeners.
         *
         * @param playerAdvancementsIn the player advancements in
         */
        public Listeners(PlayerAdvancements playerAdvancementsIn) {

            this.playerAdvancements = playerAdvancementsIn;
        }

        /**
         * Checks if is empty.
         *
         * @return true, if is empty
         */
        public boolean isEmpty() {

            return this.listeners.isEmpty();
        }

        /**
         * Adds the.
         *
         * @param listener the listener
         */
        public void add(CriterionTrigger.Listener listener) {

            this.listeners.add(listener);
        }

        /**
         * Removes the.
         *
         * @param listener the listener
         */
        public void remove(CriterionTrigger.Listener listener) {

            this.listeners.remove(listener);
        }

        /**
         * Trigger.
         *
         * @param player the player
         */
        public void trigger(ServerPlayer player) {

            List<Listener<Instance>> list = null;

            for(CriterionTrigger.Listener<CustomTrigger.Instance> listener : this.listeners) {
                if(listener.getTriggerInstance().test()) {
                    if(list == null) {
                        list = Lists.newArrayList();
                    }

                    list.add(listener);
                }
            }

            if(list != null) {
                for(CriterionTrigger.Listener listener1 : list) {
                    listener1.run(this.playerAdvancements);
                }
            }
        }
    }
}
