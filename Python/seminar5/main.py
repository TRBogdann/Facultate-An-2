import numpy as np
import numpy.random
from generate import *
from valid import *
from qualities import *
from rank import *
from permutari import *


def problema1():
    population = genPopulation(-2, 7, 20, 3, "f", isValid1)
    for it in population:
        print(it, " ", quality1(it))


def problema2():
    population = genPopulation(0, 1, 18, 5, "d", isIdentity)
    appendQuality(population, quality2)
    sortByLast(population)
    print(population)


def problema3():
    population = genPopulation3(-1, 1, 10, 9)
    values = [1, 1, 1, 3, 1, 4, 1, 1, 2, 1]
    average = 0
    for it in population:
        average += quality3(it, values)
    average = average / len(population)
    print(average)


def problema4():
    population = []
    k = int(input("Citeste k:"))
    last = factorial(k)
    for i in range(0, 15):
        population.append(nthPermutation(np.random.randint(0, last), k))
    ranked, values = rankByQuality(population, quality4)
    for i in range(0, len(ranked)):
        print(ranked[i], " ", values[i])


def problema5():
    k = int(input("Citeste k:"))
    population = genPopulation(0, 1, k, 8, "d", isValid5)
    ranked, values = rankByQuality(population, binToNum)
    for i in range(0, len(ranked)):
        print(ranked[i], " ", values[i])


def problema6():
    population = []
    k = int(input("Citeste k:"))
    last = factorial(8)
    for i in range(0, k):
        population.append(nthPermutation(np.random.randint(0, last), 8))
    ranked, values = rankByQuality(population, quality6)
    for i in range(0, len(ranked)):
        print(ranked[i], " ", values[i])


def problema7():
    k = int(input("Citeste k:"))
    population = genPopulation(1, 4, k, 8, "d", isValid7)
    appendQuality(population, quality7)
    ranked, values = rankByQuality(population, identityQuality)
    for i in range(0, len(ranked))[::-1]:
        print(ranked[i], " ", values[i])


def problema8():
    k = int(input("Citeste k:"))
    population = genPopulation(-4, 4, k, 8, "d", isValid8)
    appendQuality(population, quality8)
    ranked, values = rankByQuality(population, identityQuality)
    for i in range(0, len(ranked))[::-1]:
        print(ranked[i], " ", values[i])


def problema9():
    k = int(input("Citeste k:"))
    population = genPopulation(0, 1, 10, k, "d", isIdentity)
    appendQuality(population, quality9)
    ranked, values = rankByQuality(population, identityQuality)
    for i in range(0, len(ranked))[::-1]:
        print(ranked[i], " ", values[i])


def problema10():
    n = int(input("Citeste n:"))
    k = int(input("Citeste k:"))
    ind = []
    for i in range(0, 10):
        ind.append(k)
    population = genPopulation(-1, 1, n, 10, "d", isValid8)
    for i in range(0, len(population)):
        population[i] = np.append(population[i], quality3(population[i], ind))
    ranked, values = rankByQuality(population, identityQuality)
    for i in range(0, len(ranked)):
        print(ranked[i], " ", values[i])


def problema11():
    n = int(input("Citeste n:"))
    population = genPopulation(-2, 2, n, 3, "f", isIdentity)
    for i in range(0, len(population)):
        it = population[i]
        population[i] = np.append(it, it[0] + it[1] - it[2])
    ranked, values = rankByQuality(population, quality11)
    for i in range(0, len(ranked)):
        print(ranked[i], " ", values[i])


def problema12():
    population = genPopulation(0, 1, 10, 7, "d", isIdentity)
    appendQuality(population, quality12)
    average = 0
    for it in population:
        average += it[len(it)-1]
    average /= len(population)
    print(average)


def problema13():
    n = int(input("Citeste n:"))
    population = genPopulation(-10, 10, n, 7, "f", isValid13)
    ind = [3, 1, 4, 1, 1, 2, 1]
    for i in range(0, len(population)):
        it = population[i]
        population[i] = np.append(it, quality3(it, ind))
    ranked, values = rankByQuality(population, identityQuality)
    for i in range(0, len(ranked)):
        print(ranked[i], " ", values[i])


def problema14():
    population = []
    k = int(input("Citeste k:"))
    last = factorial(k)
    i = 0
    while i <= 10:
        ind = nthPermutation(np.random.randint(0, last), k)
        if ind[k-1] == k and ind[0] == 1:
            population.append(ind)
            i += 1
    ranked, values = rankByQuality(population, quality14)
    for i in range(0, len(ranked)):
        print(ranked[i], " ", values[i])


def problema15():
    n = int(input("Citeste n:"))
    population = genPopulation(0, 1, n, 9, "d", isValid15)
    appendQuality(population, quality15)
    print(population)


def problema16():
    n = int(input("Citeste n:"))
    last = factorial(7)
    population = []
    for i in range(0, n):
        population.append(nthPermutation(np.random.randint(0, last), 7))
    appendQuality(population, quality16)
    ranked, values = rankByQuality(population, quality16)
    for i in range(0, len(ranked)):
        print(ranked[i], " ", values[i])


def problema17():
    k = int(input("Citeste k:"))
    population = genPopulation(1, 6, 10, k, "d", isValid17)
    appendQuality(population, quality7)
    ranked, values = rankByQuality(population, identityQuality)
    for i in range(0, len(ranked))[::-1]:
        print(ranked[i], " ", values[i])


def problema18():
    n = int(input("Citeste n:"))
    population = genPopulation(-1, 1, n, 8, "d", isValid18)
    ranked, values = rankByQuality(population, quality18)
    for i in range(0, len(ranked)):
        print(ranked[i], " ", values[i])


def problema19():
    n = int(input("Citeste n:"))
    last = factorial(6)
    population = []
    i = 0
    while i <= n:
        ind = nthPermutation(np.random.randint(0, last), 6)

        for j in range(0, len(ind)):
            ind[j] -= 1
        if isValid19(ind) == 1:
            population.append(ind)
            i += 1
    ranked, values = rankByQuality(population, quality19)
    for i in range(0, len(ranked)):
        print(ranked[i], " ", values[i])


def problema20():
    population = genPopulation(-2, 4, 10, 7, "f", isIdentity)
    ranked, values = rankByQuality(population, quality20)
    for i in range(0, len(ranked)):
        print(ranked[i], " ", values[i])


if __name__ == '__main__':
    a = int(input("Selectati nr problemei(1-20): "))

    if a == 1:
        problema1()

    if a == 2:
        problema2()

    if a == 3:
        problema3()

    if a == 4:
        problema4()

    if a == 5:
        problema5()

    if a == 6:
        problema6()

    if a == 7:
        problema7()

    if a == 8:
        problema8()

    if a == 9:
        problema9()

    if a == 10:
        problema10()

    if a == 11:
        problema11()

    if a == 12:
        problema12()

    if a == 13:
        problema13()

    if a == 14:
        problema14()

    if a == 15:
        problema15()

    if a == 16:
        problema16()

    if a == 17:
        problema17()

    if a == 18:
        problema18()

    if a == 19:
        problema19()

    if a == 20:
        problema20()
        