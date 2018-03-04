all: autocatapp heapcatapp contextcatapp crashingcatapp

CFLAGS=-Wall -c

autocatapp: cat.o autocatapp.o
	$(CXX) $(LFLAGS) -o autocatapp cat.o autocatapp.o

heapcatapp: cat.o heapcatapp.o
	$(CXX) $(LFLAGS) -o heapcatapp cat.o heapcatapp.o

contextcatapp: cat.o house.o contextcatapp.o
	$(CXX) $(LFLAGS) -o contextcatapp cat.o house.o contextcatapp.o

crashingcatapp: cat.o crashingcatapp.o
	$(CXX) $(LFLAGS) -o crashingcatapp cat.o crashingcatapp.o


.cc.o:
	$(CXX) $(CFLAGS) $<

clean:
	$(RM) *.o autocatapp heapcatapp contextcatapp crashingcatapp
