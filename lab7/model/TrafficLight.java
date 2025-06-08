package model;

public class TrafficLight extends TrafficElement {
    private Colours colour;
    private int timeToSwitch;
    private Colours colourBeforeSwitch;

    public TrafficLight(String id, Colours colour, Colours colourBeforeSwitch) {
        super(id);
        this.colour = Colours.CZERWONY;
        this.timeToSwitch = 0;
        this.colourBeforeSwitch = colour;
    }

    public Colours getColour() {
        return colour;
    }

    public void setColour(Colours colour) {
        this.colour = colour;
    }

    public int getTimeToSwitch() {
        return timeToSwitch;
    }

    public Colours getColourBeforeSwitch() {
        return colourBeforeSwitch;
    }

    public void incrementTimeToSwitch() {
        timeToSwitch++;
    }

    @Override
    public void updateStatus() {
        incrementTimeToSwitch();
        switch (colour) {
            case CZERWONY -> {
                if (timeToSwitch >= 10) {
                    colour = Colours.ZOLTY;
                    colourBeforeSwitch = Colours.CZERWONY;
                    timeToSwitch = 0;
                }
            }
            case ZOLTY -> {
                if (timeToSwitch >= 2 && colourBeforeSwitch == Colours.ZIELONY) {
                    colour = Colours.CZERWONY;
                    colourBeforeSwitch = Colours.ZOLTY;
                    timeToSwitch = 0;
                } else if (timeToSwitch >= 2 && colourBeforeSwitch == Colours.CZERWONY) {
                    colour = Colours.ZIELONY;
                    colourBeforeSwitch = Colours.ZOLTY;
                    timeToSwitch = 0;
                }
            }
            case ZIELONY -> {
                if (timeToSwitch >= 10) {
                    colour = Colours.ZOLTY;
                    colourBeforeSwitch = Colours.ZIELONY;
                    timeToSwitch = 0;
                }
            }
        }
    }
}
