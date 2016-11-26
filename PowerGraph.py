import os
import sys
from snap import *
from TCMGraph import TCMGraph
from Node import Node
import matplotlib.pyplot as plt

print(sys.argv)

input = list(map(int, sys.argv[1:]))
n = input[0]
e = input[1]
G2 = GenRndPowerLaw( n , e, False)
skeches = input[2:]
G1 = TCMGraph(skeches)

name = "n"+str(n)+"-e"+str(e)+"-sketches-"+"-".join(list(map(str,skeches)))

for EI in G2.Edges():
      G1.addDEdge(Node(EI.GetSrcNId()), Node(EI.GetDstNId()))

degrees = {}
degrees2 = {}

for NI in G2.Nodes():
    id =  NI.GetId()
    inc = G1.getIncommingEdgesCount(Node(NI.GetId()))
    inc2 = NI.GetInDeg()
    out = G1.getOutgoingEdgesCount(Node(NI.GetId()))
    out2 = NI.GetOutDeg()

    degrees[out] = degrees.get(out, 0) + 1
    degrees2[out2] = degrees2.get(out2, 0) + 1
    degrees[out2] = degrees.get(out2, 0)
    degrees2[out] = degrees2.get(out, 0)

    print "id %d out: %d(%d) and in: %d(%d) "\
            % (id, out, out2, inc, inc2)

print(degrees)
print(degrees2)

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
G1.show()

