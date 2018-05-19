# Queueing-Simulator
===

# Brief Description
A queueing simulator created for my Data Structure course assignment.

# Problem Statement
You work as a programmer in Company X. Company X is an entertainment company who has setup several cinemas in different location to provide entertainment service. However, ticket sales has been not doing well recently. It was found out that a lot of customers are fed up with the queue waiting time to buy the tickets. As a result, your manager have ask you to write a program to simulate a queueing system in the cinema.

# Requirement
You are required to write a program to simulate queueing system in the cinema with the following requirements: 
  1. There are two type of customers to be served which are: VIP member and non-member customers. 
  2. The number of tickets wanted to be bought by the customer are vary in a range of 1 to 10 tickets. 
  3. There must be at least 2 queue (you can have more than 2 queues) in the queueing system. Of all the queues, there must be one priority queue which only allow VIP member to queue up. 
  4. There are 4 service counters in the queueing system. The service rate of the sales worker are as below: 
    o Counter 01: Required 10 seconds to sell 1 ticket. 
    o Counter 02: Required 15 seconds to sell 1 ticket. 
    o Counter 03: Required 30 seconds to sell 1 ticket. 
    o Counter 04: Required 15 seconds to sell 1 ticket. 
When a customer arrive at the cinema, he/she will go to the queue. If the customer is a VIP member, he/she will go to the priority queue, or else if he/she is a non-member, he/she will go to the normal queue. VIP member customer must be served before any non-member customer. Whenever there are customers waiting in the queue, the available service counters has to call the next customer and serve them. There should not be any idle service counter except there is no more customer to serve.

# Solution
Through the use of LinkedList and ArrayList as our data structure, we successfully created a queueing system.
Users may choose to customize the counter properties or use the default setting. Then, user is required to input the customer properties
Simulation can be done either in real time or skip to results.
Counter information are shown in bar chart whereas customer information are shown in XY line chart.

# Sample Input
```
Use default counter settings? (y/n): y
Enter customer inputs: 
5
0 N 4
10 N 2
20 V 5
25 N 2
30 N 1
All set! Click "Simulate" to begin!
```
# Sample Output
```
(Log) - Time: 0 | Customer enter Normal Queue | Customer enters Counter A |
(Log) - Time: 10 | Customer enter Normal Queue | Customer enters Counter B |
(Log) - Time: 20 | Customer enter VIP Queue | Customer enters Counter D | 
(Log) - Time: 25 | Customer enter Normal Queue | Customer enters Counter C | 
(Log) - Time: 30 | Customer enter Normal Queue | 
(Log) - Time: 40 | Customer left Counter A | Customer left Counter B | Customer enters Counter A | 
(Log) - Time: 50 | Customer left Counter A | 
(Log) - Time: 85 | Customer left Counter C | 
(Log) - Time: 95 | Customer left Counter D | 
Console: Simulation Ended

Total Completion Time: 95
-------------------------------------------------------------------------------------------------------------------
|Customer  |Arrival   |Start Processing    |End Processing |Processing Time     |Waiting Time   |Queue   |Counter |
|1         |0         |0                   |40             |40                  |0              |Normal  |A       |
|2         |10        |10                  |40             |30                  |0              |Normal  |B       |
|3         |20        |20                  |95             |75                  |0              |VIP     |D       |
|4         |25        |25                  |85             |60                  |0              |Normal  |C       |
|5         |30        |40                  |50             |10                  |10             |Normal  |A       |

```
** Code might be messy due to short time **
