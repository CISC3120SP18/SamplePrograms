CATAPP=autocatapp
CATAPPOBJS=autocatapp.o cat.o

all: $(CATAPP)

CFLAGS=-Wall -c

$(CATAPP): $(CATAPPOBJS)
	$(CXX) $(LFLAGS) -o $(CATAPP) $(CATAPPOBJS)

.cc.o:
	$(CXX) $(CFLAGS) $<

clean:
	$(RM) $(CATAPP) $(CATAPPOBJS)
