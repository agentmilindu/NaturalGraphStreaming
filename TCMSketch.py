class TCMSketch():
    def __init__(self, size):
        self.size = size
        self.sketch = [[None] * size for _ in range(size)]

    def _addEdge(self, x, y, w=1):
        v = self.sketch[x][y]

        if v:
            self.sketch[x][y] += w
        else:
            self.sketch[x][y] = w
        return self.sketch[x][y]

    def addDEdge(self, a, b, w=1):
        x = a.hash() % self.size
        y = b.hash() % self.size

        return self._addEdge(x, y, w)

    def addUDEdge(self, a, b):
        x = a.hash() % self.size
        y = b.hash() % self.size
        self._addEdge(x, y)
        return self._addEdge(y, x)

    def getIncommingEdgesCount(self, a):
        x = a.hash() % self.size
        count = 0
        for y in range(self.size):
            if self.sketch[y][x]:
                count += self.sketch[y][x]
        return count

    def getOutgoingEdgesCount(self, a):
        x = a.hash() % self.size
        count = 0
        for y in range(self.size):
            if self.sketch[x][y]:
                count += self.sketch[x][y]
        return count

    def show(self):
        print('\n'.join([''.join(['{:4}'.format(item) for item in row]) for row in self.sketch]))


