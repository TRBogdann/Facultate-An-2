
import numpy as np
from qualities import *
from valid import *


def problema1():
    pop = []
    n = int(input("Dimensiunea permutarilor: "))
    dim = int(input("Dimensiunea populatiei: "))
    sample = np.array([x for x in range(1, n+1)])
    for i in range(0, dim):
        np.random.shuffle(sample)
        pop.append(sample.copy())

    popQuality = pop.copy()
    for i in range(0, dim):
        popQuality[i] = np.append(popQuality[i], quality1(popQuality[i]))

    print(popQuality)

    prob = int(input("Probabilitate mutatie: "))
    for i in range(0, dim):
        num = np.random.uniform(0, 1)
        if num < prob:
            pos1 = np.random.randint(1, n)
            pos2 = pos1
            while pos2 == pos1:
                pos2 = np.random.randint(1, n)
            temp = pop[i][pos1]
            pop[i] = np.insert(np.delete(pop[i], pos1), pos2, temp)

    popQuality = pop.copy()
    for i in range(0, dim):
        popQuality[i] = np.append(popQuality[i], quality1(popQuality[i]))
    print(popQuality)


def problema2():
    pop = []
    dim = int(input("Dimensiunea populatiei: "))
    for i in range(0, dim):
        pop.append([np.binary_repr(np.random.randint(1, 1500), 12),
                    np.binary_repr(np.random.randint(-1, 2500), 12)])

    pc = int(input("Probabilitate de recombinare: "))

    popQuality = []
    for i in range(0, len(pop)):
        popQuality.append(pop[i].copy())
        popQuality[i].append(quality2(pop[i]))
    print(popQuality)

    for i in range(0, dim, 2):
        prob = np.random.uniform(0, 1)
        if prob < pc and i != dim-1:
            x = pop[i].copy()
            y = pop[i+1].copy()
            crossA = np.random.choice(range(0, 12), 3, replace=False)
            crossA.sort()
            crossB = np.random.choice(range(0, 12), 3, replace=False)
            crossB.sort()
            revA = False
            revB = False
            for j in range(0, 12):
                if j in crossA:
                    revA = not revA
                if j in crossB:
                    revB = not revB
                if revA:
                    ch = x[0][j]
                    x[0] = x[0][:j] + y[0][j] + x[0][j + 1:]
                    y[0] = y[0][:j] + ch + y[0][j + 1:]
                if revB:
                    ch = x[1][j]
                    x[1] = x[1][:j] + y[1][j] + x[1][j + 1:]
                    y[1] = y[1][:j] + ch + y[1][j + 1:]
            pop[i] = x
            pop[i+1] = y

    popQuality = []
    for i in range(0, len(pop)):
        popQuality.append(pop[i].copy())
        popQuality[i].append(quality2(pop[i]))
    print(popQuality)


def problema3():
    pop = []
    dim = int(input("Dimensiunea populatiei: "))
    for i in range(0, dim):
        ind = [np.random.uniform(-1, 1),
               np.random.uniform(0, 0.2),
               np.random.uniform(0, 1),
               np.random.uniform(0, 5)]
        pop.append(ind)

    for it in pop:
        print(it, ' ', quality3(it))

    pm = int(input("Probabilitate de mutatie: "))
    for i in range(0, dim):
        pc = np.random.uniform(0, 1)
        if pc < pm:
            pos = np.random.randint(0, 3)
            temp = pop[i][pos] + (np.random.uniform(-0.6, 0.6))
            while not isValid3(temp, pos):
                temp = pop[i][pos] + (np.random.uniform(-0.6, 0.6))
            pop[i][pos] = temp

    for it in pop:
        print(it, ' ', quality3(it))


def problema4():
    pop = []
    dim = int(input("Dimensiunea populatiei: "))
    for i in range(0, dim):
        ind = [np.random.uniform(-1, 1),
               np.random.uniform(0, 1),
               np.random.uniform(-2, 1)]
        pop.append(ind)

    popQuality = []

    i = 0
    for it in pop:
        popQuality.append(it.copy())
        popQuality[i].append(quality4(it))
        i += 1
    print(popQuality)

    pc = int(input("Probabilitate de recombinare: "))
    for i in range(0, dim, 2):
        prob = np.random.uniform(0, 1)
        if prob < pc and i != dim-1:
            x = pop[i].copy()
            y = pop[i+1].copy()
            alpha = np.random.uniform(0.2, 0.8)
            pop[i] = [it1*alpha+it2*(1-alpha) for it1, it2 in zip(x, y)]
            pop[i+1] = [it1 * alpha + it2 * (1 - alpha) for it1, it2 in zip(y, x)]

    popQuality = []
    i = 0
    for it in pop:
        popQuality.append(it.copy())
        popQuality[i].append(quality4(it))
        i += 1
    print(popQuality)


def problema5():
    pop = []
    dim = int(input("Dimensiunea populatiei: "))
    for i in range(0, dim):
        pop.append(np.random.randint(0, 2, size=7))

    popQuality = []
    i = 0
    for it in pop:
        popQuality.append(it.copy())
        popQuality[i] = np.append(popQuality[i], quality5(it))
        i += 1
    print(popQuality)

    pc = int(input("Probabilitate de recombinare: "))
    for i in range(0, dim, 2):
        prob = np.random.uniform(0, 1)
        if prob < pc and i != dim-1:
            pos = [np.random.randint(0, 7), np.random.randint(0, 7)]
            pos.sort()
            keep = True
            for j in range(0, 7):
                if keep in pos:
                    keep = not keep
                if not keep:
                    temp = pop[i+1][j]
                    pop[i+1][j] = pop[i][j]
                    pop[i][j] = temp

    popQuality = []
    i = 0
    for it in pop:
        popQuality.append(it.copy())
        popQuality[i] = np.append(popQuality[i], quality5(it))
        i += 1
    print(popQuality)


def genPopulation6(size):
    pop = []
    i = 0
    while i < size:
        num = np.random.randint(0, 2, 9)
        if isValid6(num):
            pop.append(num)
            i += 1
    return pop


def nextGeneration6(population, pc):
    popNext = []
    for i in range(0, len(population), 2):
        if i != len(population) - 1:
            x = population[i].copy()
            y = population[i+1].copy()
            prob = np.random.uniform(0, 1)
            if prob < pc:
                cross = np.random.randint(0, 9)
                for j in range(cross, 9):
                    (x[j], y[j]) = (y[j], x[j])
            if isValid6(x) and quality6(x) > quality6(population[i]):
                popNext.append(x)
            else:
                popNext.append(population[i].copy())
            if isValid6(y) and quality6(y) > quality6(population[i+1]):
                popNext.append(y)
            else:
                popNext.append(population[i+1].copy())
        else:
            popNext.append(population[i].copy)
    return popNext


def problema6():
    dim = int(input("Dimensiunea populatiei: "))
    population = genPopulation6(dim)
    for it in population:
        print(it, " ", quality6(it))

    pc = int(input("Probabilitate de recombinare:"))
    nextGen = nextGeneration6(population, pc)
    for it in nextGen:
        print(it, " ", quality6(it))


def genPopulation7(num, size):
    array = [x for x in range(1, num+1)]
    pop = []
    for i in range(size):
        ind = np.array(array)
        np.random.shuffle(ind)
        pop.append(ind)
    return pop


def appendQuality7(pop, quality):
    newPop = []
    for i in range(len(pop)):
        newPop.append(pop[i].copy())
        newPop[i] = np.append(newPop[i], quality(pop[i].copy()))
    return newPop


def nextGen7(population, pm):
    nextGen = []
    for it in population:
        prob = np.random.uniform(0, 1)
        ind = it.copy()
        if prob < pm:
            seq = [np.random.randint(0, it.size), np.random.randint(0, it.size)]
            seq.sort()
            toShuffle = np.array([ind[x] for x in range(seq[0], seq[1]+1)])
            np.random.shuffle(toShuffle)
            ind[seq[0]:seq[1]+1] = [x for x in toShuffle]
        nextGen.append(ind.copy())
    return nextGen


def problema7():
    num = int(input("Multimea permutarilor: "))
    dim = int(input("DImensiunea populatiei: "))
    population = genPopulation7(num, dim)
    print(appendQuality7(population, quality1))

    pm = int(input("Probabilitate de mutatie: "))
    nextGen = nextGen7(population, pm)
    print(appendQuality7(nextGen, quality1))


def sorter(v):
    return v[len(v)-1]


def nextGen(population1, population2):
    choose = [x.copy() for x in population1]
    for it in population2:
        choose.append(it)
    choose.sort(key=sorter, reverse=True)
    return [choose[x] for x in range(len(population1))]


def problema8():
    num = int(input("Multimea permutarilor: "))
    dim = int(input("DImensiunea populatiei: "))
    population1 = appendQuality7(genPopulation7(num, dim), quality8)
    population2 = appendQuality7(genPopulation7(num, dim), quality8)
    print(population1)
    print(population2)
    print(nextGen(population1, population2))


def genPopulation9(size):
    pop = []
    i = 0
    while i < size:
        ind = np.random.randint(0, 2, 12)
        if isValid9(ind):
            pop.append(ind)
            i += 1
    return pop


def meanPop(population):
    s = 0.0
    n = len(population)
    for it in population:
        s += quality9(it)
    return s/n


def sigmaPop(population):
    mean = meanPop(population)
    n = len(population)
    s = 0.0
    for it in population:
        s += (quality9(it) - mean)**2
    return np.sqrt(s/(n-1))


def g9(v, mean, sigma):
    return max(quality9(v)-(mean-2*sigma), 0)


def probabilityTable(population):
    mean = meanPop(population)
    sigma = sigmaPop(population)
    gf = 0
    for it in population:
        gf += g9(it, mean, sigma)
    table = []
    for it in population:
        table.append(g9(it, mean, sigma)/gf)
    return table


def parinti9(population, num):
    table = probabilityTable(population)
    selected = np.random.choice(range(0, len(population)), num, p=table)
    return [population[x].copy() for x in selected]


def problema9():
    dim = int(input("Dimensiunea populatiei: "))
    population = genPopulation9(dim)
    print(appendQuality7(population, quality9))
    print(probabilityTable(population))

    num = int(input("Numar parinti selectat:"))
    parents = parinti9(population, num)
    print(appendQuality7(parents, quality9))


def sorter10(v):
    return quality6(v)


def nextGen10(population1, population2):
    population1.sort(key=sorter10, reverse=True)
    population2.sort(key=sorter10, reverse=True)
    test = [grayToNum(x) for x in population1]
    nextGen = population1.copy()
    i = 0
    for it in population2:
        if grayToNum(it) not in test:
            nextGen[len(nextGen)-1-i] = it.copy()
            test[len(nextGen)-1-i] = grayToNum(it)
            i += 1
        if i == 2:
            break
    return nextGen


def problema10():
    dim = int(input("Dimensiunea populatiei: "))
    population1 = genPopulation6(dim)
    population2 = genPopulation6(dim)
    nextPop = nextGen10(population1, population2)
    for i in range(dim):
        print(population1[i], " ", quality6(population1[i]))
    print()
    for i in range(dim):
        print(population2[i], " ", quality6(population2[i]))
    print()
    nextPop.sort(key=sorter10, reverse=True)
    for i in range(dim):
        print(nextPop[i], " ", quality6(nextPop[i]))


def genPopulation11(dim):
    pop = []
    i = 0
    while i < dim:
        ind = np.random.randint(0, 2, 9)
        if isValid11(ind):
            pop.append(ind)
            i += 1
    return pop


def parents11(population, num):
    chosen = []
    parents = []
    dim = len(population)
    while len(parents) < num:
        x = np.random.randint(0, dim)
        y = np.random.randint(0, dim)
        if x not in chosen and y not in chosen:
            winner = y
            if quality11(population[x]) > quality11(population[y]):
                winner = y
            chosen.append(winner)
            parents.append(population[winner].copy())
    return parents


def problema11():
    dim = int(input("Dimensiunea populatiei: "))
    population = genPopulation11(dim)
    qualPop = appendQuality7(population, quality11)
    for it in qualPop:
        print(it)

    k = int(input("Selectati nr de parinti: "))
    next_gen = appendQuality7(parents11(population, k), quality11)
    for it in next_gen:
        print(it)


if __name__ == '__main__':
    ref = [problema1, problema2, problema3, problema4, problema5, problema6,
           problema7, problema8, problema9, problema10, problema11]
    a = int(input("Alege problema: "))
    if 0 < a <= 11:
        ref[a-1]()


