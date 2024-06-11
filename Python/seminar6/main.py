import numpy as np


def generatePopulation(num, size):
    pop = []
    arr = np.array([x for x in range(1, num+1)])
    for i in range(0, size):
        np.random.shuffle(arr)
        pop.append(arr.copy())
    return pop


def nextGeneration(population, size, pm):
    popNext = []
    for iter in population:
        prob = np.random.uniform(0, 1)
        ind = iter.copy()
        if prob < pm:
            inv = [np.random.randint(0, len(ind)),
                   np.random.randint(0, len(ind))]
            inv.sort()
            for i in range(inv[0], int(inv[1]/2)):
                temp = ind[i]
                ind[i] = ind[inv[1]-i+inv[0]]
                ind[inv[1]-i+inv[0]] = temp
        popNext.append(ind)

    return popNext


def quality(ind, vec):
    qual = 0
    for i in range(0, len(ind)):
        qual += ind[i] * vec[i]
    return qual


def problema1():
    vec = [x for x in range(0, 7)]
    population = generatePopulation(7, 20)
    for it in population:
        print(it, " ", quality(it, vec))

    print('\n')
    nextPop = nextGeneration(population, 20, 1)
    for it in nextPop:
        print(it, " ", quality(it, vec))


def genPopopulation2(size):
    pop = []
    for i in range(size):
        ind = [np.random.randint(1, 1501),
               np.random.randint(-1, 2501),
               np.random.randint(10, 251),
               np.random.randint(10, 250)]
        pop.append(ind)
    return pop

def quality2(v):
    return v[1]*np.sin(v[0]-2)**2+v[2]+v[3]


def appendQuality(population, qual):
    qualPop = []
    for i in range(len(population)):
        qualPop.append(population[i].copy())
        qualPop[i].append(qual(population[i]))
    return qualPop


def nextGen(population, pm):
    popNext = []
    for i in range(0, len(population), 2):
        prob = np.random.uniform(0, 1)
        if prob < pm and i != len(population)-1:
            x = population[i].copy()
            y = population[i+1].copy()
            for j in range(0, 4):
                pr = np.random.uniform(0, 1)
                if pr < 0.5:
                    (x[j], y[j]) = (y[j], x[j])
            if quality2(population[i]) > quality2(x):
                x = population[i].copy()
            if quality2(population[i+1]) > quality2(y):
                y = population[i + 1].copy()
            popNext.append(x)
            popNext.append(y)
        else:
            popNext.append(population[i].copy())
            if i != len(population)-1:
                popNext.append(population[i+1].copy())
    return popNext


def problema2():
    population = genPopopulation2(20)
    print(appendQuality(population, quality2))
    print(appendQuality(nextGen(population, 1), quality2))


def isValid(v):
    count = 0
    for it in v:
        count += it
    return count % 2

def generatePopulation3(size):
    pop = []
    i = 0
    while i < size:
        ind = np.random.randint(0, 2, 17)
        if isValid(ind):
            pop.append(ind)
            i += 1

    return pop


def quality3(v):
    res = 0
    for it in v:
        res += it
    return res


def appendQuality3(population, qual):
    qualPop = []
    for i in range(len(population)):
        qualPop.append(population[i].copy())
        qualPop[i] = np.append(qualPop[i], qual(population[i]))
    return qualPop


def nextGen3(population, k, size):
    used = []
    nextGen = []
    i = 0
    while i < k:
        x = np.random.randint(0, size)
        y = np.random.randint(0, size)
        if x not in used and y not in used:
            winner = population[x]
            if quality3(winner) < quality3(population[y]):
                winner = population[y]
            nextGen.append(winner)
            used.append(x)
            used.append(y)
            i += 1
    return nextGen


def problema3():
    population = generatePopulation3(20)
    print(appendQuality3(population, quality3))
    print('\n')
    print(appendQuality3(nextGen3(population, 10, 20), quality3))



if __name__ == '__main__':
    problema3()


