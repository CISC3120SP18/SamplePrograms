all: autocatapp heapcatapp contextcatapp crashingcatapp

CFLAGS=-Wall -c

autocatapp: cat.o autocatapp.o
	$(CXX) ${LFLAGS} -o autocatapp cat.o autocatapp.o

heapcatapp: cat.o heapcatapp.o
	$(CXX) ${LFLAGS} -o heapcatapp cat.o heapcatapp.o

crashingcatapp: cat.o crashingcatapp.o
	$(CXX) ${LFLAGS} -o crashingcatapp cat.o crashingcatapp.o

contextcatapp: cat.o house.o contextcatapp.o
	$(CXX) ${LFLAGS} -o contextcatapp cat.o house.o contextcatapp.o

.cc.o:
	$(CXX) ${CFLAGS} $<

clean:
	$(RM) *.o autocatapp heapcatapp
