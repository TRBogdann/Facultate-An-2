import numpy as np


result: list[float] = [0, 0, 1]


def eval(a: float, b: float, c: float):
    if a + b + c == 0:
        return 0
    return (6000 * a + 4200 * b + 2800 * c) / (a + b + c)


def isValid(a: float, b: float, c: float):
    if a+b+c == 0:
        return 0
    return (30 * a + 48 * b + 32 * c) / (a + b + c) > 40 and (100 * a + 60 * b + 50 * c) < 5000


def backtracking(min, max, k, li):
    if k == len(li) - 1:
        if isValid(li[0], li[1], li[2]):
            global result
            if eval(li[0], li[1], li[2]) > eval(result[0], result[1], result[2]):
                result = li.copy()
    else:
        for i in range(min, max + 1):
            li[k] = i
            backtracking(min, max, k + 1, li)


def detMax(order):
    val = [0, 0, 0]
    while (100 * val[0] + 60 * val[1] + 50 * val[2]) < 5000:
        val[order] += 1
    return val[order]


def calculVecini(ind):
    chosen = ind.copy()
    li = []
    for i in range(0, len(ind)):
        v1 = ind.copy()
        v2 = ind.copy()
        if v1[i] > 1:
            v1[i] -= 1
            if isValid(v1[0], v1[1], v2[2]) and eval(v1[0], v1[1], v1[2]) > eval(ind[0], ind[1], ind[2]):
                li.append(v1)
        if v2[i] > 1:
            v2[i] += 1
            if isValid(v2[0], v2[1], v2[2]) and eval(v2[0], v2[1], v2[2]) > eval(ind[0], ind[1], ind[2]):
                li.append(v2)

    if not len(li):
        return chosen

    s = 0
    probability_table = []
    for it in li:
        s += eval(it[0], it[1], it[2])
    for it in li:
        probability_table.append(float(eval(it[0], it[1], it[2]))/s)

    return li[np.random.choice(range(0, len(li)), 1, p=probability_table)[0]].copy()


def genPopulation(numberOfIndividuals, maxA, maxB, maxC):
    population = []
    while len(population) < numberOfIndividuals:
        element = np.random.randint([1, 1, 1], [maxA, maxB, maxC])
        if isValid(float(element[0]), float(element[1]), float(element[2])):
            population.append(element)
    return population


def isDiff(list1, list2):
    for i in range(0, len(list1)):
        for j in range(0, len(list1[0])):
            if list1[i][j] == list2[i][j]:
                return True
    return False


def rank(population):
    values = population.copy()
    score = []
    for i in range(0, len(values)-1):
        for j in range(i+1, len(values)):
            if eval(values[i][0], values[i][1], values[i][2]) < eval(values[j][0], values[j][1], values[j][2]):
                (values[i], values[j]) = (values[j], values[i])

    for it in values:
        score.append(eval(it[0], it[1], it[2]))

    return values, score


def hc(population, nrOfIt):
    prev = population.copy()
    changed = 1
    k = 0
    while k < nrOfIt and changed:
        for i in range(0, len(population)):
            population[i] = calculVecini(population[i])
        changed = isDiff(prev, population)
        prev = population.copy()
        k += 1


def sortDesc(li, i):
    for j in range(i, len(li)-1):
        for k in range(j+1, len(li)):
            if li[j] > li[k]:
                (li[k], li[j]) = (li[j], li[k])


def sortAsc(li, i):
    for j in range(i, len(li)-1):
        for k in range(j+1, len(li)):
            if li[j] < li[k]:
                (li[k], li[j]) = (li[j], li[k])

def increment(p):
    nxt = p.copy()
    i = len(nxt) - 2
    while i >= 0 and nxt[i+1] < nxt[i]:
        i -= 1
    j = len(nxt) - 1
    while nxt[j] < nxt[i]:
        j -= 1
    if i >= 0:
        (nxt[i], nxt[j]) = (nxt[j], nxt[i])
        sortDesc(nxt, i+1)
    return nxt


def decrease(p):
    nxt = p.copy()
    i = len(nxt) - 2
    while i >= 0 and nxt[i+1] > nxt[i]:
        i -= 1
    j = len(nxt) - 1
    while nxt[j] > nxt[i]:
        j -= 1
    if i >= 0:
        (nxt[i], nxt[j]) = (nxt[j], nxt[i])
        sortAsc(nxt, i+1)
    return nxt


def factorial(n):
    if n < 2:
        return 1
    else:
        return n * factorial(n-1)

def nthPermutation(n, maxN):
    p = []
    for i in range(1, maxN+1):
        p.append(i)
    for i in range(0, n):
        p = increment(p)
    return p

def evalPerm(p):
    c = 0.0
    for i in range(0, len(p)-1):
        for j in range(i+1, len(p)):
            if abs(i-j) == abs(p[i]-p[j]):
                c += 1.0
    return 1.0/(c+1.0)


def calculVeciniPer(p):
    chosen = p.copy()
    left = decrease(p)
    right = increment(p)

    if evalPerm(left) >= evalPerm(chosen):
        chosen = left.copy()

    if evalPerm(right) >= evalPerm(chosen):
        chosen = right.copy()

    return chosen


def rankPer(population):
    values = population.copy()
    score = []
    for i in range(0, len(values)-1):
        for j in range(i+1, len(values)):
            if evalPerm(values[i]) < evalPerm(values[j]):
                (values[i], values[j]) = (values[j], values[i])

    for it in values:
        score.append(evalPerm(it))

    return values, score


def hcRegine(population, nrOfIter):
    changed = 0
    iter = 0
    prev = population.copy()
    while iter < nrOfIter and changed:
        for i in range(0, len(population)):
            population[i] = calculVeciniPer(population[i])
        changed = isDiff(prev, population)
        prev = population.copy()
        iter += 1



def cerinta1():
    init = [0, 0, 1]
    backtracking(0, 50, 0, init.copy())
    print(f'Vector rezultat:{result} \n Valoare:{eval(result[0],result[1],result[2])}')


def cerinta2():
    maxA = detMax(0)
    maxB = detMax(1)
    maxC = detMax(2)
    population = genPopulation(40, maxA, maxB, maxC)
    hc(population, 40)
    values, score = rank(population)
    for i in range(0, len(values)):
        print(f'Rank: {i+1} Value:{values[i]} Score:{score[i]}')


def cerinta3():
    uncodified = np.random.randint(0, factorial(8), size=100)
    codified = []
    for it in uncodified:
        codified.append(nthPermutation(it, 8))
    hcRegine(codified, 200)
    values, score = rankPer(codified)
    for i in range(0, len(values)):
        print(f'Rank: {i+1} Value:{values[i]} Score:{score[i]}')


if __name__ == '__main__':
    print('\nCerinta1:\n')
    cerinta1()

    print('\nCerinta2:\n')
    cerinta2()

    print('\nCerinta3:\n')
    cerinta3()


