class Loader {
    private final int identifier;
    private final int numOfLoaders;

    public Loader(int numOfLoaders) {
        this.numOfLoaders = numOfLoaders;
        this.identifier = 1;
    }
    
    private Loader(int identifier, int numOfLoaders) {
        this.numOfLoaders = numOfLoaders;
        this.identifier = identifier;
    }

    public Loader nextLoader() {
        if (this.identifier == this.numOfLoaders) {
            return new Loader(1, this.numOfLoaders);
        } else {
            return new Loader(this.identifier + 1, this.numOfLoaders);
        }
    }

    public boolean isNotOnlyOne() {
        return this.numOfLoaders != 1;
    }

    public boolean isOnlyOne() {
        return this.numOfLoaders == 1;
    }

    public boolean isEnd() {
        return this.identifier + 1 > this.numOfLoaders;
    }

    @Override
    public String toString() {
        return "Loader #" + this.identifier;
    }
}
