from __future__ import division
from collections import OrderedDict
import os
import sys
import snap
from snap import GenForestFire, ConvertGraph, PUNGraph
from TCMGraph import TCMGraph
from TCMGraphAS import TCMGraphAS
from Node import Node
import matplotlib.pyplot as plt

print(sys.argv)

input = list(map(int, sys.argv[1:]))
n = input[0]
e = input[1]
G2 = GenForestFire(100000, 0.35, 0.35)
#G2 = ConvertGraph(PUNGraph,G2)
skeches = input[2:]
G1 = TCMGraph(skeches)

edC = snap.CntUniqDirEdges(G2)

name = "n"+str(n)+"-e"+str(edC)+"-sketches-"+"-".join(list(map(str,skeches)))

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

print("Querying ")
for NI in G2.Nodes():
    id =  NI.GetId()
    inc = G1.getIncommingEdgesCount(Node(NI.GetId()))
    inc2 = NI.GetInDeg()

    x = a1.get(inc - inc2, 0) + 1
    a1[inc - inc2] = x

    if(inc>inc2):
        a += 1
    if(inc<inc2):
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

print(edC)
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

