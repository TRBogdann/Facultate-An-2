import numpy as np


def result(v):
    count = 0
    for i in range(len(v) - 1):
        for j in range(i + 1, len(v)):
            if np.abs(v[i] - v[j]) == np.abs(i - j):
                count += 1
    return count


def quality(v):
    return 1/(result(v)+1)


def genPopulation(n, size):
    sample = np.array([x for x in range(0, n)])
    pop = []
    mx = [-1, -1]
    for i in range(size):
        np.random.shuffle(sample)
        pop.append(sample.copy())
        if quality(pop[i]) > mx[1]:
            mx[0] = i
            mx[1] = quality(pop[i].copy())
    return pop, mx

def rank(pop):
    for i in range(len(pop)-1):
        for j in range(i+1, len(pop)):
            if quality(pop[i]) < quality(pop[j]):
                aux = pop[j]
                pop[j] = pop[i]
                pop[i] = aux

def place(x,element,b,a):
    i = b-1
    placed = False
    while i < len(x) and not placed:
        if x[i] == -1:
            x[i] = element
            placed = True
        i += 1
    i = 0
    if not placed:
        while i < b-1 and not placed:
            if x[i] == -1:
                x[i] = element
                placed = True
            i += 1
def ocx(x, y):

    size = x.size
    new_x = [-1 for x in range(size)]
    new_y = [-1 for x in range(size)]
    a = np.random.randint(0, size)
    b = np.random.randint(0, size)
    if a > b:
        a, b = b, a
    for i in range(a, b):
        new_x[i] = y[i]
        new_y[i] = x[i]
    for i in range(b-1, size):
        if x[i] not in new_x:
            place(new_x,x[i],b,a)
        if y[i] not in new_y:
            place(new_y,y[i],b,a)
    for i in range(0, b-1):
        if x[i] not in new_x:
            place(new_x,x[i],b,a)
        if y[i] not in new_y:
            place(new_y,y[i],b,a)
        return new_x,new_y

def shuffleX(x):
    size = len(x)
    a = np.random.randint(0, size)
    b = np.random.randint(0, size)
    if a > b:
        a, b = b, a
    deck = np.array([x[y] for y in range(a, b)])
    np.random.shuffle(deck)
    for i in range(a, b):
        x[i] = deck[i-a]


def nextGen(pop, pc, pm):
    rank(pop)
    popNext = []
    mx = [-1, -1]
    for i in range(0, len(pop), 2):
        if i == len(pop)-1:
            popNext.append(pop[i].copy())
            if quality(pop[i]) > mx[1]:
                mx = [i, quality(pop[i])]
        else:
            x = pop[i].copy()
            y = pop[i+1].copy()
            prob = np.random.uniform(0, 1)
            if prob < pc:
                x, y = ocx(x, y)
                if quality(pop[i]) > quality(x):
                    x = pop[i].copy()
                if quality(pop[i+1]) > quality(y):
                    y = pop[i].copy()
            if quality(x) > mx[1]:
                mx = [i, quality(x)]
            if quality(y) > mx[1]:
                mx = [i+1, quality(y)]
            popNext.append(x)
            popNext.append(y)

    for i in range(len(popNext)):
        prob = np.random.uniform(0, 1)
        if prob < pm:
            x = popNext[i].copy()
            shuffleX(x)
            if quality(x) > quality(popNext[i]):
                popNext[i] = x
                if quality(x) > mx[1]:
                    mx = [i, quality(x)]

    return popNext, mx


def problema():
    n = int(input("Multime permutari: "))
    size = int(input("Nr indivizi: "))
    pop, mx = genPopulation(n, size)
    for it in pop:
        print(it)
    print("next:")
    popNext,new_mx = nextGen(pop, 0.8, 0.2)
    for it in popNext:
        print(it)


problema()
