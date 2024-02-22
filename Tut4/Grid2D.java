import java.util.List;

class Grid2D<E> {
    private final ImList<E> list;
    private final int numOfCols;

    Grid2D(int numOfCols) {
        this.list = new ImList<E>();
        this.numOfCols = numOfCols;
    }

    Grid2D(List<E> list, int numOfCols) {
        this.list = new ImList<E>(list);
        this.numOfCols = numOfCols;
    }

    Grid2D(ImList<E> list, int numOfCols) {
        this.list = list;
        this.numOfCols = numOfCols;
    }

    Grid2D<E> add(E elem) {
        ImList<E> newList = this.list.add(elem);
        return new Grid2D<E>(newList, this.numOfCols);
    }

    E get(int r, int c) {
        int index = c + r * this.numOfCols;
        return this.list.get(index);
    }

    Grid2D<E> set(int r, int c, E elem) {
        int index = c + r * this.numOfCols;
        return  new Grid2D<E>(this.list.set(index, elem), this.numOfCols);
    }

    public int getNumOfCols() {
        return this.numOfCols;
    }

    public int getNumOfRows() {
        return this.list.size() / this.getNumOfCols();
    }

    public int size() {
        return this.list.size();
    }

    @Override
    public String toString() {
        String newString = "{";
        int numOfElemPerRow = 0;
        for (int i = 0; i < this.list.size(); i++) {
            newString += this.list.get(i); // get each elem in ImList
            numOfElemPerRow++;

            if (i < this.list.size() - 1) {
                if (numOfElemPerRow == this.numOfCols) {
                    newString += ";";
                    numOfElemPerRow = 0;
                } else {
                    newString += ",";
                }
            }
        }
        newString += "}";
        return newString;
    }
}


