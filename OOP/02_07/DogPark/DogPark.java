class DogPark {
    private Dog buddy = new Dog("Buddy", 60);
    private Dog millie = new Dog("Millie", 10);

    public static void main(String[] args) {
        Dog buddy = new Dog(); 
        Dog millie= new Dog();
        buddy.bark();
        millie.bark();
    }

    void feedDog(Dog dog) {
        int size = dog.getSize() + 1;
        dog.setSize(size);
    }

    void feedDogs() {
        feedDog(buddy);
        feedDog(millie);
    }

    void listDogs() {
        System.out.println(buddy.getName() + ": " + buddy.getSize());
        System.out.println(millie.getName() + ": " + millie.getSize());
    }

    void play() {
        int num = (int) (Math.random() * 5);
        buddy.bark(num);

        num = (int) (Math.random() * 5);
        millie.bark(num);
    }

}
