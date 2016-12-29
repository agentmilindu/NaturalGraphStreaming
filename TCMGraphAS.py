from TCMSketch import TCMSketch

class TCMGraphAS():

    def __init__(self, sizes):
        self.sizes = sizes
        self.sketches = []
        self.sketches_count = 2
        for s in sizes[0:2]:
            self.sketches.append(TCMSketch(s))

    def addDEdge(self, a, b):
        counts = []
        for s in self.sketches:
            v = s.addDEdge(a, b)
            counts.append(v)

        m = min(counts)
        c = counts.count(m)

        if c <= 1:
            print("Min value:",m, "Number of sketches giving min value:", c, counts)
            print(self.sizes)
            s = TCMSketch(self.sizes[self.sketches_count])
            v = s.addDEdge(a, b, m)
            counts.append(v)
            self.sketches.append(s)
            print("new sketch created, size: "+str(self.sizes[self.sketches_count]))
            self.sketches_count += 1
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


