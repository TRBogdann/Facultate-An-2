from generate import *
from rank import *


def quality(x):
    return x[1]


def valid(x):
    return 1 == 1


def newGen():
    return genPopulation(0, 100, 20, 2, 'f', valid)


def elitism(pop, nSelected):
    rankedPop, qualitiesPop = rankByQuality(pop, quality)
    keep = []
    for i in range(0, nSelected):
        keep.append(rankedPop[i])

    newPop, qualitiesPop = rankByQuality(newGen(), quality)
    for i in range(0, nSelected):
        newPop[len(newPop)-i-1] = keep[i]

    return newPop


if __name__ == '__main__':
    population = genPopulation(0, 100, 20, 2, 'f', valid)
    ranked, qualities = rankByQuality(population, quality)
    for it in ranked:
        print(it)
    ranked, qualities = rankByQuality(elitism(population, 3), quality)

    for it in ranked:
        print(it)
