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
G1 = TCMGraphAS(skeches)

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


count = 0
correct0 = 0
correct = 0
correct1 = 0
correct2 = 0
correct3 = 0
for NI in G2.Edges():

    if count == 1000:
        break
    else:
        count += 1

    sid = NI.GetSrcNId()
    did = NI.GetDstNId()

    ev = G1.getEdge(Node(sid), Node(did))
    if ev is None:
        correct0 += 1
    elif ev is 1:
        correct += 1
    elif ev is 2:
        correct1 += 1
    elif ev is 3:
        correct2 += 1
    elif ev is 4:
        correct3 += 1

for NI in G2.Nodes():
    id =  NI.GetId()
    inc = G1.getIncommingEdgesCount(Node(NI.GetId()))
    inc2 = NI.GetInDeg()

    x = a1.get(inc - inc2, 0) + 1
    a1[inc - inc2] = x

    if(inc>inc2):
        aa += (inc - inc2)/inc2
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

print(correct0, correct, correct1, correct2, correct3)

print(degrees)
print(degrees2)
print(a, b, c)
print(aa, bb, cc)
print(a1)

#incomming_sorted = sorted(degrees.items(), key=lambda value: value[1])

plt.xlabel('degrees')
plt.ylabel('freequency')
plt.title('Degree freequency')

plt.plot(degrees.keys(), degrees.values())
plt.plot(degrees2.keys(), degrees2.values(), linestyle='--')

#if not os.path.exists("plots/"+name):
#    os.makedirs("plots/"+name)

plt.savefig("plots/"+name+"plot.png")
plt.show()
#G1.show()

