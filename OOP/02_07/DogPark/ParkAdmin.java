class ParkAdmin {
    public static void main(String[] args) {
        DogPark park = new DogPark();
        park.listDogs();
        park.play();
        feedAllDogs(park);
        park.listDogs();
        Dog dog = park.getRandomDog();
        System.out.println(dog.getName());
    }

    static void feedAllDogs(DogPark park) {
        Dog[] dogs = park.getAllDogs();

        if (dogs.length >= 2) {
            Dog buddy = dogs[0];
            park.feedDog(buddy);
            Dog millie = dogs[1];
            park.feedDog(millie);
        }
    }
}
