abstract class TrafficLight {
    protected final String color;

    TrafficLight(String color) {
        this.color = color;
    }

    abstract TrafficLight toggle() ;

    @Override
    public String toString() {
        return this.color;
    }
}

// qn 2a
class RedLight extends TrafficLight {
    
    RedLight() {
        super("red");
    }

    TrafficLight toggle() {
        return new GreenLight();
    }
}

class GreenLight extends TrafficLight {
    
    GreenLight() {
        super("green");
    }

    TrafficLight toggle() {
        return new RedLight();
    }
}

// qn 2b
// toggling (new RedLight(), 5);

abstract class RedLight extends TrafficLight {
    
    RedLight() {
        super("red");
    }

    abstract TrafficLight toggle() ;
}

abstract class GreenLight extends TrafficLight {
    
    GreenLight() {
        super("green");
    }

    abstract TrafficLight toggle() ;
}

abstract class AmberLight extends TrafficLight {
    
    AmberLight() {
        super("amber");
    }

    abstract TrafficLight toggle() ;
}

// qn 2c
// in jshell
RedLight red2c = new RedLight() {
    private final RedLight red = this;
    TrafficLight toggle() {
        return new GreenLight() {
            TrafficLight toggle() {
                return new AmberLight() {
                    TrafficLight toggle() {
                        return red;
                    };
                };
            };
        };
    };
};


// qn 2d
// in jshell
RedLight red2d = new RedLight() {
    private final RedLight red = this;
    TrafficLight toggle() {
        return new AmberLight() {
            TrafficLight toggle() {
                return new GreenLight() {
                    TrafficLight toggle() {
                        return new AmberLight() {
                            TrafficLight toggle() {
                                return red;
                            };
                        };
                    };
                };
            };
        };
    };
};