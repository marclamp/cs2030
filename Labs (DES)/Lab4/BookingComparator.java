class ListingComparator implements Comparator<Pair<Service, Integer>> {
    public int compare(Pair<Service, Integer> pair1, Pair<Service, Integer> pair2) {
        if (pair1.second() == pair2.second()) {
            return  
        } else {
            return pair1.second() - pair2.second();
        }
    }
}
