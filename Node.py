import mmh3

class Node():
    def __init__(self, val):
        self.val  = val

    def hash(self, seed=0):
        return  mmh3.hash128(str(self.val), seed)
