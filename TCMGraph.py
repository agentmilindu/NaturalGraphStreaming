from TCMSketch import TCMSketch

class TCMGraph():

    def __init__(self, sizes):
        self.sizes = sizes
        self.sketches = []

        for s in sizes:
            self.sketches.append(TCMSketch(s))

    def addDEdge(self, a, b):
        counts = []
        for s in self.sketches:
            v = s.addDEdge(a, b)
            counts.append(v)
        return counts

    def addUDEdge(self, a, b):
        for s in self.sketches:
            s.addDEdge(a, b)

    def getIncommingEdgesCount(self, a):
        counts = []
        for s in self.sketches:
            counts.append(s.getIncommingEdgesCount(a))

        #print(counts, min(counts))
        return min(filter(None, counts))

    def getOutgoingEdgesCount(self, a):
        counts = []
        for s in self.sketches:
            counts.append(s.getOutgoingEdgesCount(a))
        return min(counts)
    def show(self):
        for s in self.sketches:
            s.show()


