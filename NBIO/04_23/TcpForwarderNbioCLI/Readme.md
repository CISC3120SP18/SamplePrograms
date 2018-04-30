# A Simple TCP Message Forwarder

## Non-Blocking I/O
We demonstrates non-blocking I/O in this application. The application is also
an example for blocking I/O and Java threads. 

We would like to demonstrate the non-blocking I/O.  In Java only
`SelectableChannels` can be in the non-blocking mode. At present, only pipe and
sockets have `SelectableChannels`. In this example, we choose to use TCP-based
channels, i.e., `SocketChannel`, and `ServerSocketChannel`.

In Java, non-blocking I/O channels work in tandem with `Selectors`.  Only
channels that is in non-blocking mode can be registered to the selector. First,
a channel that cannot be put in non-blocking mode cannot be used with
Selectors. Second, a channel that can be the non-blocking mode must be placed
in the non-blocking mode first to register with a `Selector`.  Notice that
any `SelectableChannel` object can be placed in non-blocking mode.

##  Application Design
This application consists of two pieces, a server and a client. The server is
to receive a message from a client, select randomly a client from the list of
clients that has connected to the server, and forward the message to the
selected client. 

### Server

The server uses a `Selector` to serve multiple any number of clients permitted
by the available resources. The server uses non-blocking I/O.

### Client

Although the client can work with non-blocking I/O as well, in this
application, we provide two implementations of the client, one is
using the `Socket` and the Stream-based I/O, and the other the 
`SocketChannel` and the channel-based I/O. Both clients consists of
3 threads. 

The client can use the non-blocking I/O as well, but is yet to be
implemented. 





