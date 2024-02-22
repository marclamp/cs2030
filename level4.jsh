void serveCruises(List<Cruise> cruises, int numOfLoaders) {
    Loader loaders = new Loader(numOfLoaders);
    int currentTime = cruises.get(0).getArrivalTime();
    
    for (Cruise cruise : cruises) {
        int numOfLoadersUsed = 0;
        int serviceTimes = 1;
        int numOfServiceRequired = (int) Math.ceil(cruise.getNumOfLoadersRequired() /
            (double) numOfLoaders);
        int nextBerthAvailTime = currentTime + cruise.getServiceTime() * numOfServiceRequired;

        if (currentTime < cruise.getArrivalTime()) {
            currentTime = cruise.getArrivalTime();
        }

        while (serviceTimes <= numOfServiceRequired) {
            do {
                System.out.println(new Service(loaders, cruise, currentTime).toString());
                numOfLoadersUsed++;
                
                if (numOfLoadersUsed == numOfLoaders || numOfLoadersUsed ==
                    cruise.getNumOfLoadersRequired()) {
                    currentTime += cruise.getServiceTime();
                }

                loaders = loaders.nextLoader();

            } while (loaders.isNotOnlyOne() && numOfLoadersUsed < cruise.getNumOfLoadersRequired());
            serviceTimes++;
            if (loaders.isOnlyOne()) {
                numOfLoadersUsed = 0;
            }
        }

        if (currentTime < nextBerthAvailTime) {
            currentTime = nextBerthAvailTime;
        }
    }
}
