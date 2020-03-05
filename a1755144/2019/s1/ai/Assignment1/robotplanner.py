import argparse
import Queue

class Node:
    count = 0
    width = 0
    height = 0
    endX = 0
    endY = 0
    graph = list()
    queue = Queue.PriorityQueue()

    def __init__(self, x, y, cost):
        self.x = x
        self.y = y
        self.cost = cost
        self.branches = dict()

    @staticmethod
    def isValid(x,y):
        return x >= 0 and x < Node.width and y >= 0 and y < Node.height and Node.graph[y][x][0] == 0 and Node.graph[y][x][1] == False

    def h(self):
        return abs(Node.endX - self.x) + abs(Node.endY - self.y)

    def f(self):
        return self.h() + self.cost

    def expand(self):
        self.branches['R'] = self.move(self.x + 1, self.y)
        self.branches['L'] = self.move(self.x - 1, self.y)
        self.branches['D'] = self.move(self.x, self.y + 1)
        self.branches['U'] = self.move(self.x, self.y - 1)

    def move(self, x, y):
        if Node.isValid(x,y):
            Node.count = Node.count + 1
            newBranch = Node(x, y, self.cost + 1)
            Node.queue.put((newBranch.f(), newBranch))
            Node.graph[y][x][1] = True
            return newBranch
        else:
            return None

def printRoute(node):
    action = ''
    if node is None:
        return False, action
    if node.h() == 0:
        return True, action
    else:
        for action, branch in node.branches.items():
            result = printRoute(branch)
            if result[0] == True:
                return True, action + ' ' + result[1]
    return False, 'no solution'

parser = argparse.ArgumentParser()
parser.add_argument('fileName', help='environment')
parser.add_argument('startX', help='startX')
parser.add_argument('startY', help='startY')
parser.add_argument('endX', help='endX')
parser.add_argument('endY', help='endY')
args = parser.parse_args()

startX = int(args.startX)
startY = int(args.startY)
Node.endX = int(args.endX)
Node.endY = int(args.endY)

env = open(args.fileName, "r")
content = env.read().split()
Node.width = int(content.pop(0))
Node.height = int(content.pop(0))

for j in range(Node.height):
    row = list()
    for i in range(Node.width):
        row.append([int(content.pop(0)), False])
    Node.graph.append(row)

if Node.isValid(startX, startY) and Node.isValid(Node.endX, Node.endY):
    Node.count = Node.count + 1
    start = Node(startX, startY, 0)
    Node.queue.put((start.f(), start))

while Node.queue.qsize() > 0:
    candidate = Node.queue.get()[1]
    if candidate.h() == 0:
        print Node.count, candidate.cost
        print printRoute(start)[1]
        exit()
    else:
        candidate.expand()
print Node.count, 0
print'X'
