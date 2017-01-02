from __future__ import division
from collections import OrderedDict
import os
import sys
from snap import *
from TCMGraph import TCMGraph
from TCMGraphAS import TCMGraphAS
from Node import Node
import matplotlib.pyplot as plt

print(sys.argv)

input = list(map(int, sys.argv[1:]))
n = input[0]
e = input[1]
G2 = GenRndGnm(PNGraph, n, e)
skeches = input[2:]
G1 = TCMGraph(skeches)

name = "n"+str(n)+"-e"+str(e)+"-sketches-"+"-".join(list(map(str,skeches)))

for EI in G2.Edges():
      vs = G1.addDEdge(Node(EI.GetSrcNId()), Node(EI.GetDstNId()))
      print "Sketch values:", vs, "Min value:",min(vs)

degrees = {}
degrees2 = {}

a = 0
b = 0
c = 0

aa = 0.0
bb = 0.0
cc = 0.0

a1 = {}
b1 = {}
c1 = {}


for NI in G2.Nodes():
    id =  NI.GetId()
    inc = G1.getIncommingEdgesCount(Node(NI.GetId()))
    inc2 = NI.GetInDeg()

    x = a1.get(inc - inc2, 0) + 1
    a1[inc - inc2] = x

    if abs(inc - inc2) <= 3:
        c1 = b1.get(inc2, {})
        c1[inc - inc2] = c1.get(inc - inc2, 0) + 1
        print("c1:", c1)
        b1[inc2] = c1

    if(inc>inc2):
        aa += (inc - inc2)/inc2
        print((inc - inc2)/inc2)
        a += 1
    if(inc<inc2):
        bb += (inc2 - inc)/inc2
        b += 1
    if(inc==inc2):
        c += 1

    out = G1.getOutgoingEdgesCount(Node(NI.GetId()))
    out2 = NI.GetOutDeg()

    degrees[inc] = degrees.get(inc, 0) + 1
    degrees2[inc2] = degrees2.get(inc2, 0) + 1
    degrees[inc2] = degrees.get(inc2, 0)
    degrees2[inc] = degrees2.get(inc, 0)

    #print "id %d out: %d(%d) and in: %d(%d) "\  % (id, out, out2, inc, inc2)

print(degrees)
print(degrees2)
print(a, b, c)
print(aa, bb, cc)
print(a1)
e = OrderedDict(sorted(b1.items(), key=lambda t: t[0]))
print(e)
es0 = {}
es1 = {}
es2 = {}
es3 = {}

for i in e:
    for j in e[i]:
        if abs(j) == 0:
            es0[i] = e[i][j]
        elif abs(j) == 1:
            es1[i] = e[i][j]
        elif abs(j) == 2:
            es2[i] = e[i][j]
        elif abs(j) == 3:
            es3[i] = e[i][j]

print("---------------")
print(es0)
print(es1)
print(es2)
print(es3)



#incomming_sorted = sorted(degrees.items(), key=lambda value: value[1])

plt.xlabel('degrees')
plt.ylabel('number of queries')
plt.title('Number of effective queries')

#plt.plot(degrees.keys(), degrees.values())
#plt.plot(degrees2.keys(), degrees2.values(), linestyle='--')

#if not os.path.exists("plots/"+name):
#    os.makedirs("plots/"+name)

a2 = OrderedDict(sorted(a1.items(), key=lambda t: t[0]))
plt.plot(es1.keys(), es1.values(), color='green')
plt.plot(es2.keys(), es2.values(), color='orange')
plt.plot(es3.keys(), es3.values(), color='purple')
plt.savefig("plots/"+name+"plot.png")
plt.show()
#G1.show()

