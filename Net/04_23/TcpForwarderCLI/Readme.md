# A Simple TCP Message Forwarder

This application consists of two pieces, a server and a client. The server is to receive a message
from a client, select randomly a client from the list fo clients that has connected to the server,
and forward the message to the selected client. 

We design the server (in the `edu.cuny.brooklyn.net.server` package) with multiple threads, and 
it can accept TCP connections from any number of clients permitted by the system resources. 

When a client connects to the server, the server adds the client to a pool of clients and assign
an ID to the client. When the server receives a message from a client, it select randomly a
client from the pool, and sends the message to the client. The selected client can also be
the client that sends the message. 

We design the client with two threads. One is to receive a user's input from the Standard Input 
and sends the input as a part of the message to the server, and the other receve a message from
the server, and displays on the Standard Output.

A message in this application has three fields, seperated by ",". The first field is for recording
the id of the source client where the message comes from, and the second field the destination
client where the message is sent to. The third field is the payload (or data), the input of a user.

We use "-1" for either the source or the destination to indicate either there is no need to identify the source or the destination.  

## Task
The task is the "TODO" in the `TcpForwarderClientApp` class. 

