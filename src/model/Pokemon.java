package model;

public class Pokemon {
    private String name;
    private int defaultHealth;
    private int health;
    private int damage;
    private TypeEnum type;
    private SpecialPower specialPower;
    private WeatherConditionEnum weatherWeakness;

    public Pokemon(String name, int health, int damage, TypeEnum type, SpecialPower specialPower, WeatherConditionEnum weatherWeakness) {
        this.name = name;
        this.health = health;
        this.defaultHealth = health;
        this.damage = damage;
        this.type = type;
        this.specialPower = specialPower;
        this.weatherWeakness = weatherWeakness;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public SpecialPower getSpecialPower() {
        return specialPower;
    }

    public void setSpecialPower(SpecialPower specialPower) {
        this.specialPower = specialPower;
    }

    public WeatherConditionEnum getWeatherWeakness() {
        return weatherWeakness;
    }

    public void setWeatherWeakness(WeatherConditionEnum weatherWeakness) {
        this.weatherWeakness = weatherWeakness;
    }

    public int getDefaultHealth() {
        return defaultHealth;
    }

    public void setDefaultHealth(int defaultHealth) {
        this.defaultHealth = defaultHealth;
    }

    public int specialAttack() {
        if (getSpecialPower().getRemainingRights() > 0) {
            this.specialPower.setRemainingRights(this.specialPower.getRemainingRights() - 1);
            return this.damage + this.getSpecialPower().getExtraDamage();
        } else {
            System.out.println("You can not use special power!");
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", type=" + type +
                ", specialPower=" + specialPower +
                ", weatherWeakness=" + weatherWeakness +
                '}';
    }
}
