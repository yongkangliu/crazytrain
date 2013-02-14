package crazytrain;

public class Computer {
    private String modelName;
    private int memory;
    private int cpuSpeed;
    private int videoCard;
    private int price;

    private boolean loud = false;
    private boolean cheap = false;
    private boolean expensive = false;
    private boolean hot = false;
    private boolean risky = false;
    private boolean game = false;
    private boolean crunchNumbers = false;

    public String rulesOpinion() {
        String result = "";
        if (loud) {
            result += "loud,";
        }
        if (cheap) {
            result += "cheap,";
        }
        if (expensive) {
            result += "expensive,";
        }
        if (hot) {
            result += "hot,";
        }
        if (game) {
            result += "game,";
        }
        if (crunchNumbers) {
            result += "crunchNumbers,";
        }
        if (risky) {
            result += "risky";
        }

        if ("".equals(result)) {
            return result;
        } else {
            return result += ".";
        }
    }

    public boolean isLoud() {
        return loud;
    }

    public void setLoud(boolean loud) {
        this.loud = loud;
    }

    public boolean isCheap() {
        return cheap;
    }

    public void setCheap(boolean cheap) {
        this.cheap = cheap;
    }

    public boolean isExpensive() {
        return expensive;
    }

    public void setExpensive(boolean expensive) {
        this.expensive = expensive;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public boolean isRisky() {
        return risky;
    }

    public void setRisky(boolean risky) {
        this.risky = risky;
    }

    public boolean isGame() {
        return game;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    public boolean isCrunchNumbers() {
        return crunchNumbers;
    }

    public void setCrunchNumbers(boolean crunchNumbers) {
        this.crunchNumbers = crunchNumbers;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(int cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public int getVideoCard() {
        return videoCard;
    }

    public void setVideoCard(int videoCard) {
        this.videoCard = videoCard;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
