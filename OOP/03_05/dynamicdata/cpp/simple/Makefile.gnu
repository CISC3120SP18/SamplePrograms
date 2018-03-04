all: numberapp

CFLAGS=-Wall -c
numberapp: numberapp.o numberutil.o 
	$(CXX) $(LFLAGS) -o numberapp numberapp.o numberutil.o

.cc.o:
	$(CXX) $(CFLAGS) $<

clean:
	$(RM) numberapp.o numberutil.o numberapp
