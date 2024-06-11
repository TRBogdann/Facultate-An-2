import numpy as np


def genPopulation(lower, upper, size, num, dataType, checkFunction):
    population = []
    i = 0
    while i <= size:
        x = None
        if dataType == "f":
            x = (upper-lower)*np.random.random(num) + lower
        else:
            x = np.random.randint(lower, upper+1, num)
        if checkFunction(x):
            population.append(x)
            i += 1
    return population


def genPopulation3(lower, upper, size, num):
    population = []
    i = 0
    while i <= size:
        x = np.random.randint(lower, upper, num)
        mySum = 0
        for it in x:
            mySum += it
        x = np.append(x, mySum)
        if -1 <= mySum <= 1:
            i += 1
            population.append(x)
    return population


def appendQuality(population, quality):
    for i in range(0, len(population)):
        a = quality(population[i])
        population[i] = np.append(population[i], a)