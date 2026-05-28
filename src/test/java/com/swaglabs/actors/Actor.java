package com.swaglabs.actors;

public class Actor {
    private final String name;
    private BrowseTheMobileApp mobileAbility;

    public Actor(String name) {
        this.name = name;
    }

    public static Actor named(String name) {
        return new Actor(name);
    }

    public void can(BrowseTheMobileApp mobileAbility) {
        this.mobileAbility = mobileAbility;
    }

    public String getName() {
        return name;
    }

    public BrowseTheMobileApp getMobileAbility() {
        if (mobileAbility == null) {
            throw new IllegalStateException("El actor " + name + " no tiene la habilidad de navegar en la app móvil instalada.");
        }
        return mobileAbility;
    }
}