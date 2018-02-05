class Dog {
    private String name;
    private int size;

    Dog() {
        name = "Uknown";
        size = -1;
    }

    Dog(String nm, int sz) {
        name = nm;
        size = sz;
    }

    void bark() {
        if (size > 60) {
            System.out.println("Woof!");
        } else if (size > 14) {
            System.out.println("Ruff!");
        } else {
            System.out.println("Yip!");
        }
    }

    void bark(int numOfBarks) {
        for (int i=0; i<numOfBarks; i++) {
            bark();
        }
    }

    String getName() {
        return name;
    }

    int getSize() {
        return size;
    }

    void setName(String nm) {
        name = nm;
    }

    void setSize(int sz) {
        size = sz;
    }
}
