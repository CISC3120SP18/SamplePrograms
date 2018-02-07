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

    Dog[] getAllDogs() {
        Dog[] dogs = new Dog[2];
        dogs[0] = buddy;
        dogs[1] = millie;
        return dogs;
    }

    Dog getRandomDog() {
        int dogNo = (int) (Math.random() * 2);
        if (dogNo == 0)  {
            return buddy;
        } else {
            return millie;
        }
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
